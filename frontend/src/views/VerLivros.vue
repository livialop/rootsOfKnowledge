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
      <div class="list-section">
        <h2>Lista de Livros</h2>

        <div class="search-box">
          <input
            type="text"
            v-model="busca"
            placeholder="Buscar livro..."
          />
        </div>

        <div class="livros-list">
          <div
            v-for="(l, index) in livrosFiltrados"
            :key="index"
            class="livro-card"
            @click="verDetalhes(l)"
          >
            <h3>{{ l.titulo }}</h3>
            <p class="descricao" v-if="l.nomeAutor">Autor: {{ l.nomeAutor }}</p>
            <p class="descricao" v-if="l.genero">Gênero: {{ l.genero }}</p>
            

            <div class="card-actions">
              <button class="edit">Editar</button>
              <button class="delete" @click="deletarLivro($event, l, index)">
                Excluir
              </button>
            </div>
          </div>
        </div>
      </div>

      <div class="mascote-container">
        <img src="/img/cacto.png" class="mascote" alt="cacto" />
      </div>
    </div>
  </div>
</template>

<script>
import { getLivros, deleteLivro } from "../actions/verLivrosActions.js";

export default {
  name: "VerLivros",

  data() {
    return {
      busca: "",
      livros: []
    };
  },

  computed: {
    livrosFiltrados() {
      const termo = (this.busca || "").toLowerCase();
      return (this.livros || []).filter((l) =>
        (l.titulo || "").toLowerCase().includes(termo)
      );
    }
  },

  methods: {
    verDetalhes(livro) {
      this.$router.push(`/livro/${livro.id}`);
    },

    async deletarLivro(event, livro, index) {
      event.stopPropagation();
      if (confirm("Tem certeza que deseja excluir este livro?")) {
        try {
          await deleteLivro(livro.id);
          this.livros.splice(index, 1);
        } catch (err) {
          console.error("Erro ao excluir livro:", err);
          alert("Erro ao excluir o livro. Tente novamente.");
        }
      }
    }
  },

  async mounted() {
    try {
      const data = await getLivros();
      this.livros = Array.isArray(data) ? data : [];
    } catch (err) {
      console.error("Erro ao buscar livros:", err);
      this.livros = [];
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

.list-section {
  width: 550px;
  background: #fff7ec;
  padding: 30px;
  border-radius: 24px;
  border: 3px solid #f4c542;
  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.15);
  animation: fade-in 0.4s ease;
}

.list-section h2 {
  color: #d94e1f;
  text-transform: uppercase;
  margin-bottom: 20px;
  font-size: 22px;
}

.search-box input {
  width: 100%;
  padding: 12px;
  border: 2px solid #f4c542;
  border-radius: 12px;
  margin-bottom: 20px;
  font-size: 16px;
}

.livros-list {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.livro-card {
  background: #fff;
  border: 2px solid #f4c542;
  padding: 20px;
  border-radius: 18px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.12);
  transition: 0.25s;
  animation: fade-in 0.4s ease;
  cursor: pointer;
}

.livro-card:hover {
  transform: scale(1.02);
}

.livro-card h3 {
  font-size: 18px;
  color: #c64922;
}

.descricao {
  font-size: 14px;
  margin-top: 6px;
  opacity: 0.8;
}

.card-actions {
  display: flex;
  justify-content: flex-end;
  gap: 12px;
  margin-top: 15px;
}

button {
  padding: 8px 14px;
  border-radius: 10px;
  font-size: 14px;
  cursor: pointer;
  border: none;
  transition: 0.25s;
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