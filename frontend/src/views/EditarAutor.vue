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
        <h2>Editar Autor</h2>
        <p class="subtitle">Atualize as informações do autor.</p>

        <form @submit.prevent="submitForm" v-if="autor">
          <div class="field full">
            <label>Nome do Autor</label>
            <input
              v-model="autor.nome"
              type="text"
              placeholder="Ex: Ariano Suassuna"
              required
            />
          </div>

          <div class="field full">
            <label>Link do Portfólio</label>
            <textarea
              v-model="autor.portfolioAutor"
              placeholder="Ex: https://meuportfolio.com"
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

      <div class="ilustracao-container">
        <img
          src="/img/pergaminho-pena.png"
          alt="Pergaminho com pena"
          class="ilustracao"
        />
      </div>
    </div>
  </div>
</template>

<script>
import { getAutor, editarAutor } from '@/actions/verAutorActions';

export default {
  name: 'EditarAutor',
  data() {
    return {
      autor: null
    };
  },
  async mounted() {
    try {
      const id = this.$route.params.id;
      this.autor = await getAutor(id);
    } catch (error) {
      console.error('Erro ao carregar autor:', error);
      alert('Erro ao carregar autor.');
      this.$router.push('/ver-autor');
    }
  },
  methods: {
    voltar() {
      this.$router.push('/ver-autor');
    },
    async submitForm() {
      try {
        const id = this.$route.params.id;
        await editarAutor(id, {
          nome: this.autor.nome,
          portfolioAutor: this.autor.portfolioAutor
        });
        alert('Autor atualizado com sucesso!');
        this.$router.push('/ver-autor');
      } catch (error) {
        console.error('Erro ao atualizar autor:', error);
        alert('Erro ao atualizar autor. Tente novamente.');
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

.topo {
  background: #fff3df;
  padding: 20px 40px 0;
}

.topo-texto {
  line-height: 1.1;
}

.biblioteca {
  font-size: 13px;
  letter-spacing: 4px;
  color: #2f6f4e;
  font-weight: 600;
}

.topo-texto h1 {
  margin: 6px 0 18px;
  font-size: 38px;
  color: #ff6a00;
  font-weight: 700;
}

.faixa-xilo {
  height: 10px;
  background: repeating-linear-gradient(
    90deg,
    #ffe066 0 14px,
    #ffd43b 14px 28px,
    #ffec99 28px 42px
  );
}

.content {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 50px;
  gap: 60px;
}

.form-card {
  width: 500px;
  background: #fff;
  padding: 40px;
  border-radius: 20px;
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
  animation: fade-in 0.5s ease;
}

h2 {
  color: #ff6a00;
  margin-bottom: 8px;
  font-size: 26px;
}

.subtitle {
  margin-bottom: 25px;
  font-size: 14px;
  opacity: 0.75;
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
  color: #5c3d2e;
}

input,
textarea {
  width: 100%;
  padding: 12px 14px;
  border: 2px solid #e0e0e0;
  border-radius: 10px;
  font-size: 15px;
  font-family: inherit;
  transition: 0.25s;
}

input:focus,
textarea:focus {
  outline: none;
  border-color: #ff6a00;
  box-shadow: 0 2px 8px rgba(255, 106, 0, 0.2);
}

textarea {
  resize: vertical;
  min-height: 80px;
}

.button-group {
  display: flex;
  gap: 12px;
  justify-content: flex-end;
  margin-top: 10px;
}

button {
  padding: 12px 28px;
  border-radius: 10px;
  font-size: 16px;
  cursor: pointer;
  border: none;
  transition: 0.25s;
  font-weight: 600;
}

button[type="submit"] {
  background: #ff6a00;
  color: white;
}

button[type="submit"]:hover {
  background: #e05c00;
  transform: scale(1.03);
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
  color: #ff6a00;
}

.ilustracao-container {
  display: flex;
  align-items: center;
}

.ilustracao {
  width: 320px;
  filter: drop-shadow(0 4px 12px rgba(0, 0, 0, 0.15));
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

/* HEADER */
/* ===== NAVBAR NOVA ===== */


header {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  background: #f9eedf !important;;
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

/* ===== DROPDOWN ===== */
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

/* ===== RESPONSIVIDADE ===== */
@media (max-width: 768px) {
  header {
    padding: 16px;
  }

  .navbar-nav {
    flex-direction: column;
    align-items: flex-start;
    gap: 12px;
  }

  .dropdown-menu {
    position: relative;
    top: 0;
    box-shadow: none;
  }
}

</style>
