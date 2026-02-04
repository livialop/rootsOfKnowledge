<template>
  <div class="page-container">
    <header class="header">
      <h1>BIBLIOTECA RAÍZES DO SABER</h1>
    </header>

    <!-- Faixa decorativa estilo xilogravura -->
    <div class="faixa"></div>

    <div class="content">
      <div class="form-card">
        <h2>Novo Livro</h2>
        <p class="subtitle">Cadastre um novo livro no acervo da biblioteca.</p>

        <form @submit.prevent="submitForm">
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
              v-model="livro.numeroPaginas"
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

          <button type="submit">Salvar Livro</button>
        </form>
      </div>

      <div class="mascote-container">
        <img src="/img/cacto.png" alt="Mascote cacto" class="mascote" />
      </div>
    </div>
  </div>
</template>

<script>
import { createNovoLivro } from '@/actions/novoLivroActions';

export default {
  name: 'NovoLivro',
  data() {
    return {
      livro: {
        titulo: '',
        subTitulo: '',
        preco: '',
        sumario: '',
        numeroPaginas: '',
        isbn: '',
        autorId: null,
        generoId: null,
        conteudo: ''
      },
      autores: [],
      generos: []
    };
  },
  created() {
    this.loadAutoresGeneros();
  },
  methods: {
    async loadAutoresGeneros() {
      try {
        const { getAutores } = await import('@/actions/verAutorActions');
        const { getGeneros } = await import('@/actions/verGeneroActions');
        this.autores = await getAutores();
        this.generos = await getGeneros();
      } catch (error) {
        console.error('Erro ao carregar autores/gêneros', error);
      }
    },
    async submitForm() {
      try {
        // const { createNovoLivro } = await import('@/actions/novoLivroActions');
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
        console.log('Payload novo livro:', payload);
        await createNovoLivro(payload);
        alert('Livro salvo com sucesso.');
        this.livro = {
          titulo: '',
          subTitulo: '',
          preco: '',
          sumario: '',
          numeroPaginas: '',
          isbn: '',
          autorId: null,
          generoId: null,
          conteudo: ''
        };
      } catch (error) {
        console.error('Erro ao criar livro: ', error);
        alert('Erro ao criar livro. Tente novamente.');
      }
    }
  }
}

</script>


<style scoped>
/* ---------------------------- */
/* FUNDO E ESTRUTURA GERAL      */
/* ---------------------------- */
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

/* ---------------------------- */
/* LAYOUT                       */
/* ---------------------------- */
.content {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 40px;
  gap: 60px;
}

/* ---------------------------- */
/* FORM CARD                    */
/* ---------------------------- */
.form-card {
  width: 520px;
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
}

input,
textarea,
select {
  width: 100%;
  padding: 12px;
  border-radius: 12px;
  border: 2px solid #f4c542;
  background: #fff;
  margin-top: 6px;
  transition: 0.25s;
}

input:focus,
textarea:focus,
select:focus {
  border-color: #e85a2a;
  box-shadow: 0 0 6px rgba(232, 90, 42, 0.4);
}

textarea {
  resize: none;
  height: 120px;
}

/* BOTÃO */
button {
  grid-column: span 2;
  padding: 14px;
  font-size: 18px;
  background: #e85a2a;
  color: #fff;
  border: none;
  border-radius: 20px;
  cursor: pointer;
  transition: 0.3s;
  box-shadow: 0 6px 12px rgba(232, 90, 42, 0.3);
}

button:hover {
  background: #c64922;
  transform: scale(1.04);
}

/* ---------------------------- */
/* MASCOTE                      */
/* ---------------------------- */
.mascote {
  width: 400px;
  animation: mascoteAnim 2.8s ease-in-out infinite;
}

@keyframes mascoteAnim {
  0% { transform: rotate(0deg) translateY(0); }
  25% { transform: rotate(2deg) translateY(-4px); }
  50% { transform: rotate(0deg) translateY(0); }
  75% { transform: rotate(-2deg) translateY(-3px); }
  100% { transform: rotate(0deg) translateY(0); }
}

@keyframes fade-in {
  from {
    opacity: 0;
    transform: translateY(25px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
</style>