<template>
  <div class="page-container">
    <header class="header">
      <h1>DETALHES DO LIVRO</h1>
    </header>

    <div class="faixa"></div>

    <div class="content">
      <div class="detail-section">
        <div v-if="livro" class="livro-detalhes">
          <h2>{{ livro.titulo }}</h2>
          
          <div class="info-group" v-if="livro.subTitulo">
            <label>Subtítulo:</label>
            <p>{{ livro.subTitulo }}</p>
          </div>

          <div class="info-group">
            <label>Autor:</label>
            <p>{{ livro.nomeAutor || 'Não informado' }}</p>
          </div>

          <div class="info-group">
            <label>Gênero:</label>
            <p>{{ livro.genero || 'Não informado' }}</p>
          </div>

          <div class="info-group" v-if="livro.isbn">
            <label>ISBN:</label>
            <p>{{ livro.isbn }}</p>
          </div>

          <div class="info-group" v-if="livro.numeroPaginas">
            <label>Número de Páginas:</label>
            <p>{{ livro.numeroPaginas }}</p>
          </div>

          <div class="info-group" v-if="livro.preco">
            <label>Preço:</label>
            <p>R$ {{ livro.preco }}</p>
          </div>

          <div class="info-group" v-if="livro.sumario">
            <label>Sumário:</label>
            <p>{{ livro.sumario }}</p>
          </div>

          <div class="info-group" v-if="livro.conteudo">
            <label>Conteúdo:</label>
            <p>{{ livro.conteudo }}</p>
          </div>

          <div class="action-buttons">
            <button class="back" @click="voltar">Voltar</button>
            <button class="edit" @click="editar">Editar</button>
            <button class="delete" @click="deletar">Excluir</button>
          </div>
        </div>

        <div v-else class="loading">
          <p>Carregando...</p>
        </div>
      </div>

      <!-- Mascote -->
      <div class="mascote-container">
        <img src="/img/cacto.png" class="mascote" alt="cacto" />
      </div>
    </div>
  </div>
</template>

<script>
import { getLivro, deleteLivro } from "../actions/verLivrosActions.js";

export default {
  name: "VerLivro",

  data() {
    return {
      livro: null
    };
  },

  methods: {
    voltar() {
      this.$router.push('/ver-livros');
    },

    editar() {
      this.$router.push(`/editar-livro/${this.livro.id}`);
    },

    async deletar() {
      if (confirm("Tem certeza que deseja excluir este livro?")) {
        try {
          await deleteLivro(this.livro.id);
          alert("Livro excluído com sucesso!");
          this.$router.push('/ver-livros');
        } catch (err) {
          console.error("Erro ao excluir livro:", err);
          alert("Erro ao excluir o livro. Tente novamente.");
        }
      }
    }
  },

  async mounted() {
    try {
      const id = this.$route.params.id;
      const data = await getLivro(id);
      this.livro = data;
    } catch (err) {
      console.error("Erro ao buscar livro:", err);
      alert("Erro ao carregar os detalhes do livro");
      this.$router.push('/ver-livros');
    }
  }
};
</script>

<style scoped>
/* Fundo */
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
  font-size: 28px;
  color: #5c3d2e;
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
  gap: 50px;
  padding: 40px;
}

/* Detalhes do livro */
.detail-section {
  width: 600px;
  background: #fff7ec;
  padding: 40px;
  border-radius: 24px;
  border: 3px solid #f4c542;
  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.15);
  animation: fade-in 0.4s ease;
}

.livro-detalhes h2 {
  color: #c64922;
  font-size: 28px;
  margin-bottom: 30px;
  text-align: center;
}

.info-group {
  margin-bottom: 20px;
  padding: 15px;
  background: #fff;
  border-radius: 12px;
  border: 2px solid #f4c542;
}

.info-group label {
  display: block;
  font-weight: 600;
  color: #d94e1f;
  margin-bottom: 8px;
  font-size: 16px;
}

.info-group p {
  margin: 0;
  color: #5c3d2e;
  font-size: 15px;
  line-height: 1.6;
}

.action-buttons {
  display: flex;
  justify-content: center;
  gap: 12px;
  margin-top: 30px;
}

button {
  padding: 12px 24px;
  border-radius: 10px;
  font-size: 16px;
  cursor: pointer;
  border: none;
  transition: 0.25s;
  font-weight: 500;
}

.back {
  background: #8b8b8b;
  color: white;
}

.back:hover {
  background: #6b6b6b;
}

.edit {
  background: #f4c542;
  color: #5c3d2e;
}

.edit:hover {
  background: #d7a62b;
}

.delete {
  background: #e85a2a;
  color: white;
}

.delete:hover {
  background: #c64922;
}

.loading {
  text-align: center;
  padding: 40px;
  font-size: 18px;
  color: #d94e1f;
}

/* Mascote */
.mascote-container {
  display: flex;
  justify-content: center;
  align-items: center;
}

.mascote {
  width: 310px;
  animation: mascoteAnim 2.8s ease-in-out infinite;
}

/* Mascote animado */
@keyframes mascoteAnim {
  0% {
    transform: rotate(0deg);
  }
  25% {
    transform: rotate(1.6deg);
  }
  50% {
    transform: rotate(0deg);
  }
  75% {
    transform: rotate(-1.8deg);
  }
  100% {
    transform: rotate(0deg);
  }
}

/* Fade-in */
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
