<template>
  <div class="page-container">
    <header class="header">
      <h1>AUTORES CADASTRADOS</h1>
    </header>

    <div class="faixa"></div>

    <div class="content">
      <div class="list-section">
        <h2>Lista de Autores</h2>
        <div class="search-box">
          <input
            type="text"
            v-model="busca"
            placeholder="Buscar autor..."
          />
        </div>
        <div class="autores-list">
          <div
            v-for="(a, index) in autoresFiltrados"
            :key="index"
            class="autor-card"
          >
            <h3>Nome: {{ a.nome }} <br> Portfólio: {{ a.portfolioAutor }}</h3>
            

            <div class="card-actions">
              <button class="edit" @click="editarAutor(index)">Editar</button>
              <button class="delete" @click="deletarAutor(index)">
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
import { getAutores, deleteAutor } from "../actions/verAutorActions.js";

export default {
  name: "VerAutores",

  data() {
    return {
      busca: "",
      autores: []
    };
  },

  computed: {
    autoresFiltrados() {
      const termo = (this.busca || "").toLowerCase();
      return (this.autores || []).filter((a) =>
        (a.nome || "").toLowerCase().includes(termo)
      );
    }
  },

  methods: {
    editarAutor(index) {
      const autor = this.autores[index];
      this.$router.push(`/editar-autor/${autor.id}`);
    },

    async deletarAutor(index) {
      if (confirm("Tem certeza que deseja excluir este autor?")) {
        try {
          const autor = this.autores[index];
          await deleteAutor(autor.id);
          this.autores.splice(index, 1);
        } catch (err) {
          console.error("Erro ao excluir autor:", err);
          if (err.response && err.response.data) {
            alert(err.response.data);
          } else {
            alert("Erro ao excluir o autor. Tente novamente.");
          }
        }
      }
    }
  },

  async mounted() {
    try {
      const data = await getAutores();
      this.autores = Array.isArray(data) ? data : [];
    } catch (err) {
      console.error("Erro ao buscar autores:", err);
      this.autores = [];
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

.autores-list {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.autor-card {
  background: #fff;
  border: 2px solid #f4c542;
  padding: 20px;
  border-radius: 18px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.12);
  transition: 0.25s;
  animation: fade-in 0.4s ease;
}

.autor-card:hover {
  transform: scale(1.02);
}

.autor-card h3 {
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
}

.mascote {
  width: 310px;
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
