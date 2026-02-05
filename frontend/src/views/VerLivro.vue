<template>
  <div class="page-container">
    <!-- HEADER -->
    <header>
      <nav id="navbar">
        <div id="navbarCollapse">
          <ul class="nav navbar-nav">
            <li><router-link to="/">Home</router-link></li>
            <li><router-link to="/ver-livros">Livros</router-link></li>
            <li><router-link to="/ver-genero">Gêneros</router-link></li>
            <li><router-link to="/ver-autor">Autores</router-link></li>

            <li class="dropdown">
              <a href="#" class="dropdown-toggle">
                Cadastros
              </a>
              <ul class="dropdown-menu">
                <li><router-link to="/novo-livro">Novo Livro</router-link></li>
                <li><router-link to="/novo-genero">Novo Gênero</router-link></li>
                <li><router-link to="/novo-autor">Novo Autor</router-link></li>
              </ul>
            </li>

            <li class="dropdown">
              <a href="#" class="dropdown-toggle">
                Edições
              </a>
              <ul class="dropdown-menu">
                <li><router-link to="/editar-livro/1">Editar Livro</router-link></li>
                <li><router-link to="/editar-genero/1">Editar Gênero</router-link></li>
                <li><router-link to="/editar-autor/1">Editar Autor</router-link></li>
              </ul>
            </li>
          </ul>
        </div>
      </nav>
    </header>

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
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap');

:root {
    --color-primary-1: #fff9ea;
    --color-primary-2: #ffe8b4;
    --color-primary-3: #f8d477;
    --color-primary-4: #ffe100;
    --color-primary-5: #ffcb45;
    --color-primary-6: #e9a209;
    --color-neutral-0: #fff;
    --color-neutral-1: #1d1d1d;
}

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: 'Poppins', sans-serif;
}

.page-container {
  background-color: #fff4e6;
  min-height: 100vh;
}

/* HEADER */
header {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  background: #f9eedf !important;
  padding: 10px 8%;
  z-index: 999;
  display: flex;
  align-items: center;
}

#navbarCollapse {
  width: 100%;
}

.navbar-nav {
  display: flex;
  list-style: none;
  gap: 24px;
  align-items: center;
}

.navbar-nav > li {
  position: relative;
}

.navbar-nav a {
  text-decoration: none;
  font-weight: 500;
  color: var(--color-neutral-1);
  padding: 8px 6px;
  transition: 0.2s;
}

.navbar-nav a:hover {
  color: var(--color-primary-6);
}

/* DROPDOWN */
.dropdown:hover .dropdown-menu {
  display: block;
}

.dropdown-menu {
  display: none;
  position: absolute;
  top: 34px;
  left: 0;
  background: white;
  list-style: none;
  padding: 10px 0;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0,0,0,0.15);
  min-width: 160px;
  z-index: 1000;
}

.dropdown-menu li {
  width: 100%;
}

.dropdown-menu a {
  display: block;
  padding: 10px 16px;
  color: var(--color-neutral-1);
}

.dropdown-menu a:hover {
  background: #ffe8b4;
  color: #000;
}

.content {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  gap: 50px;
  padding: 120px 40px 40px;
}

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

.mascote-container {
  display: flex;
  justify-content: center;
  align-items: center;
  background: transparent;
  mix-blend-mode: multiply;
}

.mascote {
  width: 500px;
  opacity: 0.95;
  filter: brightness(1.05) contrast(1.1);
  animation: mascoteAnim 2.8s ease-in-out infinite;
}


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
