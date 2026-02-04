<template>
  <div class="page-container">
    <header class="header">
      <h1>EDITAR LIVRO</h1>
    </header>

    <div class="faixa"></div>

    <div class="content">
      <div class="form-card">
        <h2>Editar Livro</h2>
        <p class="subtitle">Atualize as informações do livro no acervo.</p>

        <form @submit.prevent="submitForm" v-if="livro">
          <div class="field full">
            <label>Título</label>
            <input
              v-model="livro.titulo"
              type="text"
              placeholder="Ex: Vidas Secas"
              max="100"
              required
            />
          </div>

          <div class="field full">
            <label>Subtítulo</label>
            <input
              v-model="livro.subTitulo"
              type="text"
              placeholder="Ex: Uma história de resistência"
              max="50"
              required
            />
          </div>

          <div class="field full">
            <label>Preço</label>
            <input
              v-model.number="livro.preco"
              type="number"
              step="0.01"
              placeholder="Ex: 29.90"
              min="15"
              required
            />
          </div>

          <div class="field full">
            <label>Sumário</label>
            <input
              v-model="livro.sumario"
              type="text"
              placeholder="Ex: 3 capítulos"
              required
            />
          </div>

          <div class="field full">
            <label>Número de Páginas</label>
            <input
              v-model.number="livro.numeroPaginas"
              type="number"
              placeholder="Ex: 150"
              min="30"
              required
            />
          </div>

          <div class="field full">
            <label>ISBN</label>
            <input
              v-model="livro.isbn"
              type="text"
              placeholder="Ex: 9788535902778"
              minlength="13"
              maxlength="13"
              required
            />
          </div>

          <div class="field">
            <label>Autor</label>
            <select v-model.number="livro.autorId" required>
              <option disabled value="">Selecione o autor</option>
              <option :value="autor.id" v-for="autor in autores" :key="autor.id">
                {{ autor.nome }}
              </option>
            </select>
          </div>

          <div class="field">
            <label>Gênero</label>
            <select v-model.number="livro.generoId" required>
              <option disabled value="">Selecione o gênero</option>
              <option :value="genero.id" v-for="genero in generos" :key="genero.id">
                {{ genero.nome }}
              </option>
            </select>
          </div>

          <div class="field full">
            <label>Conteúdo</label>
            <textarea
              v-model="livro.conteudo"
              placeholder="Conteúdo da obra..."
              required
            ></textarea>
          </div>

          <div class="button-group">
            <button type="button" class="cancel" @click="voltar">Cancelar</button>
            <button type="submit">Salvar Alterações</button>
          </div>
        </form>

        <div v-else class="loading">Carregando...</div>
      </div>

      <div class="mascote-container">
        <img src="/img/cacto.png" alt="Mascote cacto" class="mascote" />
      </div>
    </div>
  </div>
</template>

<script>
import { getLivro, editarLivro } from '@/actions/verLivrosActions';
import { getAutores } from '@/actions/verAutorActions';
import { getGeneros } from '@/actions/verGeneroActions';

export default {
  name: 'EditarLivro',
  data() {
    return {
      livro: null,
      autores: [],
      generos: []
    };
  },
  async mounted() {
    try {
      const id = this.$route.params.id;
      await this.loadAutoresGeneros();
      const livroData = await getLivro(id);
      
      // Precisamos buscar os IDs dos autores e gêneros pelo nome
      const autor = this.autores.find(a => a.nome === livroData.nomeAutor);
      const genero = this.generos.find(g => g.nome === livroData.genero);
      
      this.livro = {
        titulo: livroData.titulo,
        subTitulo: livroData.subTitulo,
        preco: livroData.preco,
        sumario: livroData.sumario,
        numeroPaginas: livroData.numeroPaginas,
        isbn: livroData.isbn,
        autorId: autor?.id || null,
        generoId: genero?.id || null,
        conteudo: livroData.conteudo
      };
    } catch (error) {
      console.error('Erro ao carregar livro:', error);
      alert('Erro ao carregar livro.');
      this.$router.push('/ver-livros');
    }
  },
  methods: {
    async loadAutoresGeneros() {
      try {
        this.autores = await getAutores();
        this.generos = await getGeneros();
      } catch (error) {
        console.error('Erro ao carregar autores/gêneros', error);
      }
    },
    voltar() {
      this.$router.push('/ver-livros');
    },
    async submitForm() {
      try {
        const id = this.$route.params.id;
        const payload = {
          titulo: this.livro.titulo,
          subTitulo: this.livro.subTitulo,
          preco: this.livro.preco,
          sumario: this.livro.sumario,
          numeroPaginas: this.livro.numeroPaginas,
          isbn: this.livro.isbn,
          autorId: this.livro.autorId,
          generoId: this.livro.generoId,
          conteudo: this.livro.conteudo
        };
        await editarLivro(id, payload);
        alert('Livro atualizado com sucesso!');
        this.$router.push('/ver-livros');
      } catch (error) {
        console.error('Erro ao atualizar livro:', error);
        alert('Erro ao atualizar livro. Tente novamente.');
      }
    }
  }
};
</script>

<style scoped>
.page-container {
  background: linear-gradient(to bottom, #f9eedf, #f3e3ce);
  min-height: 100vh;
  font-family: "Poppins", sans-serif;
  color: #5c3d2e;
}

.header {
  background: linear-gradient(90deg, #f4c542, #f29f32);
  padding: 25px;
  text-align: center;
  font-weight: 700;
  font-size: 30px;
  color: #5c3d2e;
  letter-spacing: 2px;
}

.faixa {
  width: 100%;
  height: 16px;
  background-image: repeating-linear-gradient(
    90deg,
    #f4c542 0 22px,
    #e39b27 22px 44px
  );
}

.content {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  padding: 40px;
  gap: 60px;
}

.form-card {
  width: 600px;
  background: #fff7ec;
  padding: 35px;
  border-radius: 24px;
  border: 3px solid #f4c542;
  box-shadow: 0 6px 18px rgba(0, 0, 0, 0.18);
  animation: fade-in 0.5s ease;
}

h2 {
  color: #d94e1f;
  text-transform: uppercase;
  margin-bottom: 10px;
  font-size: 22px;
}

.subtitle {
  margin-bottom: 20px;
  font-size: 15px;
  opacity: 0.8;
}

form {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;
}

.full {
  grid-column: span 2;
}

label {
  font-weight: 600;
  display: block;
  margin-bottom: 8px;
}

input,
select,
textarea {
  width: 100%;
  padding: 12px;
  border-radius: 12px;
  border: 2px solid #f4c542;
  font-size: 15px;
  transition: 0.25s;
  font-family: inherit;
}

input:focus,
select:focus,
textarea:focus {
  outline: none;
  border-color: #e39b27;
  box-shadow: 0 2px 8px rgba(243, 197, 66, 0.3);
}

textarea {
  resize: vertical;
  min-height: 120px;
}

.button-group {
  grid-column: span 2;
  display: flex;
  gap: 12px;
  justify-content: flex-end;
}

button {
  padding: 12px 30px;
  border-radius: 12px;
  font-size: 16px;
  cursor: pointer;
  border: none;
  transition: 0.25s;
  font-weight: 600;
}

button[type="submit"] {
  background: linear-gradient(135deg, #f4c542, #e39b27);
  color: #5c3d2e;
}

button[type="submit"]:hover {
  transform: scale(1.03);
  box-shadow: 0 4px 12px rgba(243, 197, 66, 0.4);
}

.cancel {
  background: #8b8b8b;
  color: white;
}

.cancel:hover {
  background: #6b6b6b;
}

.loading {
  text-align: center;
  padding: 40px;
  font-size: 18px;
  color: #d94e1f;
}

.mascote-container {
  display: flex;
  justify-content: center;
  align-items: center;
}

.mascote {
  width: 260px;
  animation: mascoteAnim 2.8s ease-in-out infinite;
}

@keyframes mascoteAnim {
  0%, 100% { transform: rotate(0deg); }
  25% { transform: rotate(1.6deg); }
  50% { transform: rotate(0deg); }
  75% { transform: rotate(-1.8deg); }
}

@keyframes fade-in {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
  }
}
</style>
