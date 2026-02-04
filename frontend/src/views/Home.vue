<template>
  <div class="home">

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

    <!-- MAIN -->
    <main id="content">

      <!-- HOME -->
      <section id="home">
        <div class="shape"></div>

        <div id="cta">
          <h1 class="title">
            Biblioteca <span>Raízes <br> do Saber</span>
          </h1>

          <p class="description">
            Um espaço para preservar histórias, culturas e conhecimentos que atravessam gerações.
          </p>


          <div id="cta_buttons">
           <a href="#acervo" data-v-inspector="src/views/Home.vue:12:32" data-v-2dc54a20="" class="btn-default">Ver acervo</a>
          </div>

        </div>

        <div class="hero-images">
          <img src="/img/wave.png" class="main-img" />
        </div>
      </section>

      <!-- ACERVO -->
      <!-- ACERVO -->
      <section id="acervo">
        <h2 class="section-title">Acervo</h2>
        <h3 class="section-subtitle">Conhecimentos em destaque</h3>

        <div id="dishes">

          <!-- DISH 1 -->
          <div class="dish">
            <h3 class="dish-title">Gerenciar Cadastros</h3>

            <div class="dish-actions">
              <router-link to="/novo-livro" class="btn-default">Novo Livro</router-link>
              <router-link to="/novo-genero" class="btn-default">Novo Gênero</router-link>
              <router-link to="/novo-autor" class="btn-default">Novo Autor</router-link>
            </div>
          </div>

          <!-- DISH 2 -->
          <div class="dish">
            <h3 class="dish-title">Consultar Acervo</h3>

            <div class="dish-actions">
              <router-link to="/ver-livros" class="btn-default">Ver Livros</router-link>
              <router-link to="/ver-genero" class="btn-default">Ver Gêneros</router-link>
              <router-link to="/ver-autor" class="btn-default">Ver Autores</router-link>
            </div>
          </div>

          <!-- DISH 3 -->
          <div class="dish">
            <h3 class="dish-title">Editar Informações</h3>

           <div class="dish-actions">
              <router-link to="/editar-livro/1" class="btn-default">Editar Livro</router-link>
              <router-link to="/editar-genero/1" class="btn-default">Editar Gênero</router-link>
              <router-link to="/editar-autor/1" class="btn-default">Editar Autor</router-link>
            </div>
          </div>

        </div>
      </section>

    </main>

  </div>
</template>


<script>
import ScrollReveal from "scrollreveal";

export default {
  name: "Home",
  data() {
    return {
      menuOpen: false,
    };
  },
  methods: {
    toggleMenu() {
      this.menuOpen = !this.menuOpen;
    },
  },
  mounted() {
    ScrollReveal().reveal("#cta, .dish", {
      origin: "left",
      distance: "20%",
      duration: 2000,
    });
  },
};
</script>

<style scoped>
/* Seu CSS permanece TODO igual */
/* nada removido, nada alterado */
</style>


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

.home {
  background-color: #fff4e6;
  min-height: 100vh;
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


.btn-default {
  background-color: #ffcb45;
  border: none;
  border-radius: 10px;
  padding: 10px 16px;
  color: white;
  font-weight: 600;
  cursor: pointer;
}

/* HERO */
#home {
  position: relative;
  display: flex;
  align-items: center;
  min-height: 100vh;
  padding: 140px 8% 60px;
  overflow: hidden;
}

.hero-shape {
  position: absolute;
  right: -10%;
  top: 50%;
  transform: translateY(-50%);
  width: 60%;
  height: 80%;
  background-color: var(--color-primary-2);
  border-radius: 50% 40% 30% 60%;
  z-index: 1;
}

/* TEXTO */
#cta {
  width: 45%;
  display: flex;
  flex-direction: column;
  gap: 24px;
  z-index: 2;
}

#cta .title {
  font-size: 3.8rem;
  font-weight: 700;
  line-height: 1.1;
  color: var(--color-neutral-1);
}

#cta .title span {
  color: var(--color-primary-3);
}

.description {
  font-size: 1rem;
  max-width: 420px;
  color: #444;
}

/* BOTÕES */
.cta-actions {
  display: flex;
  gap: 20px;
  align-items: center;
  margin-top: 10px;
}

.btn-primary {
  background-color: #ffc107;
  border: none;
  padding: 14px 24px;
  border-radius: 12px;
  font-weight: 600;
  cursor: pointer;
}

.phone-box {
  display: flex;
  align-items: center;
  gap: 10px;
  background: white;
  padding: 12px 18px;
  border-radius: 12px;
  box-shadow: 0 5px 15px rgba(0,0,0,.1);
  font-weight: 500;
}

/* REDES */
.social-media-buttons {
  display: flex;
  gap: 14px;
  margin-top: 10px;
}

.social-media-buttons a {
  width: 42px;
  height: 42px;
  background: white;
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 5px 15px rgba(0,0,0,.1);
  color: #000;
}

/* IMAGEM */
.hero-images {
  width: 55%;
  position: relative;
  z-index: 2;
  display: flex;
  justify-content: center;
}

.hero-images .main-img {
  width: 80%;
  max-width: 450px;
  border-radius: 50%;
}


/* ACERVO */
#acervo {
  min-height: 100vh;
  text-align: center;
}

#dishes {
  display: flex;
  gap: 24px;
  margin-top: 40px;
  justify-content: center; /* CENTRALIZA */
}

.dish {
  background: white;
  padding: 20px;
  border-radius: 20px;
  width: 25%;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 16px;
}

.dish-actions {
  display: flex;
  flex-direction: column;
  gap: 10px;
  width: 100%;
}

.dish-actions .btn-default {
  width: 100%;
}


/* DEPOIMENTOS */
#depoimentos {
  display: flex;
  gap: 40px;
  align-items: center;
  min-height: 100vh;
}

/* FOOTER */
footer {
  background: var(--color-primary-2);
}
</style>