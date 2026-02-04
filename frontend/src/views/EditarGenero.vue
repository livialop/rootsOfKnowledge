<template>
  <div class="page-container">
    <header class="header">
      <h1>EDITAR GÊNERO</h1>
    </header>

    <div class="faixa"></div>

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
  align-items: center;
  padding: 40px;
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
