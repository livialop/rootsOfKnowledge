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
      <div class="form-card">
        <h2>Adicionar Gênero</h2>
        <p class="subtitle">Cadastre um novo gênero literário para a biblioteca.</p>

        <form @submit.prevent="submitForm">
          <div class="field full">
            <label>Nome do Gênero</label>
            <input 
              v-model="genero.nome" 
              type="text" placeholder="Ex: Romance" 
              required
            />
          </div>

          <button type="submit">Salvar Gênero</button>
        </form>
      </div>

      <div class="mascote-container">
        <img src="/img/cacto.png" alt="Mascote cacto" class="mascote" />
      </div>
    </div>
  </div>
</template>

<script>
import { createNovoGenero } from '@/actions/novoGeneroActions';

export default {
  name: 'NovoGenero',
  data() {
    return {
      genero: {
        nome: ''
      }
    };
  },
  methods: {
    async submitForm() {
      try {
        await createNovoGenero(this.genero);
        alert('Gênero criado com sucesso!');
        this.genero.nome = '';
      } catch (error) {
        console.error('Erro ao criar gênero: ', error);
        alert('Erro ao criar gênero. Tente novamente.');
      }
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
  align-items: center;
  padding: 120px 40px 40px;
  gap: 60px;
}

.form-card {
  width: 480px;
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
textarea {
  width: 100%;
  padding: 12px;
  border-radius: 12px;
  border: 2px solid #f4c542;
  background: #fff;
  margin-top: 6px;
  transition: 0.25s;
}

input:focus,
textarea:focus {
  border-color: #e85a2a;
  box-shadow: 0 0 6px rgba(232, 90, 42, 0.4);
}

textarea {
  resize: none;
  height: 120px;
}

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
    transform: rotate(0deg) translateY(0);
  }
  25% {
    transform: rotate(2deg) translateY(-4px);
  }
  50% {
    transform: rotate(0deg) translateY(0);
  }
  75% {
    transform: rotate(-2deg) translateY(-3px);
  }
  100% {
    transform: rotate(0deg) translateY(0);
  }
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
