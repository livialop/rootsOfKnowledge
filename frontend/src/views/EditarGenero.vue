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
        <h2>Editar Gênero</h2>
        <p class="subtitle">Atualize as informações do gênero literário.</p>

        <form @submit.prevent="submitForm" v-if="genero">
          <div class="field full">
            <label>Nome do Gênero</label>
            <input 
              v-model="genero.nome" 
              type="text" 
              placeholder="Ex: Romance" 
              required
            />
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
import { getGenero, editarGenero } from '@/actions/verGeneroActions';

export default {
  name: 'EditarGenero',
  data() {
    return {
      genero: null
    };
  },
  async mounted() {
    try {
      const id = this.$route.params.id;
      this.genero = await getGenero(id);
    } catch (error) {
      console.error('Erro ao carregar gênero:', error);
      alert('Erro ao carregar gênero.');
      this.$router.push('/ver-genero');
    }
  },
  methods: {
    voltar() {
      this.$router.push('/ver-genero');
    },
    async submitForm() {
      try {
        const id = this.$route.params.id;
        await editarGenero(id, { nome: this.genero.nome });
        alert('Gênero atualizado com sucesso!');
        this.$router.push('/ver-genero');
      } catch (error) {
        console.error('Erro ao atualizar gênero:', error);
        if (error.response && error.response.data) {
          alert(error.response.data);
        } else {
          alert('Erro ao atualizar gênero. Tente novamente.');
        }
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
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.full {
  width: 100%;
}

label {
  font-weight: 600;
  display: block;
  margin-bottom: 8px;
}

input {
  width: 100%;
  padding: 12px;
  border-radius: 12px;
  border: 2px solid #f4c542;
  font-size: 15px;
  transition: 0.25s;
}

input:focus {
  outline: none;
  border-color: #e39b27;
  box-shadow: 0 2px 8px rgba(243, 197, 66, 0.3);
}

.button-group {
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
