import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import NovoAutor from '../views/NovoAutor.vue'
import NovoGenero from '../views/NovoGenero.vue'
import VerGenero from '@/views/VerGenero.vue'
import VerAutor from '@/views/VerAutor.vue'
import NovoLivro from '@/views/NovoLivro.vue'
import VerLivros from '@/views/VerLivros.vue'
import VerLivro from '@/views/VerLivro.vue'
import EditarGenero from '@/views/EditarGenero.vue'
import EditarAutor from '@/views/EditarAutor.vue'
import EditarLivro from '@/views/EditarLivro.vue'
const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/novo-autor',
    name: 'NovoAutor',
    component: NovoAutor
  },
  {
    path: '/novo-genero',
    name: 'NovoGenero',
    component: NovoGenero
  },

  {
    path: '/ver-genero',
    name: 'VerGenero',
    component: VerGenero
  },
  {
    path: '/ver-autor',
    name: 'VerAutor',
    component: VerAutor
  },
  {
    path: '/novo-livro',
    name: 'NovoLivro',
    component: NovoLivro
  },
  {
    path: '/ver-livros',
    name: 'VerLivros',
    component: VerLivros
  },
  {
    path: '/livro/:id',
    name: 'VerLivro',
    component: VerLivro
  },
  {
    path: '/editar-genero/:id',
    name: 'EditarGenero',
    component: EditarGenero
  },
  {
    path: '/editar-autor/:id',
    name: 'EditarAutor',
    component: EditarAutor
  },
  {
    path: '/editar-livro/:id',
    name: 'EditarLivro',
    component: EditarLivro
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router