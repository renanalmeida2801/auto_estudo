<template>
  <div class="container">
    <h1>Cadastrar Curso</h1>
    <CursoFormComponent
      :curso="cursoSelecionado"
      :editando="editando"
      @cancelar="cancelarEdicao"
      @salvar="salvarCurso"
    />

    <hr/>

    <h2>Cursos</h2>
    <CursoTableComponent
      :cursos="cursos"
      @editar="editarCurso"
      @excluir="excluirCurso"
    />
  </div>
</template>

<script lang="ts" setup>
import {ref, onMounted} from 'vue'
import CursoFormComponent from '../components/form/CourseFormComponent.vue'
import CursoTableComponent from '../components/table/CursoTableComponent.vue'

const cursos = ref([])
const cursoSelecionado = ref(null)
const editando = ref(false)

const API_URL = 'http://localhost:8080/cursos'

const carregarCursos = async()=>{
  const response = await fetch(API_URL)
  cursos.value = await response.json()
}

const salvarCurso = async(curso)=>{
  const url = editando.value? `${API_URL}/${curso.id}`: API_URL
  const method = editando.value? 'PUT' : 'POST'

  await fetch(url, {
    method,
    headers: {'Content-type': 'application/json'},
    body: JSON.stringify({
      nome: curso.nome,
      sigla: curso.sigla,
      turno: curso.turno
    })
  })

  await carregarCursos()
  cursoSelecionado.value = null
  editando.value = false
}

const editarCurso = (curso) =>{
  cursoSelecionado.value = {...curso}
  editando.value = true
}

const cancelarEdicao = ()=>{
  cursoSelecionado.value = null
  editando.value = false
}

const excluirCurso = async(id) =>{
  if(confirm('Tem certeza que deseja excluir?')){
    await fetch(`${API_URL}/${id}`, {method: 'DELETE'})
    await carregarCursos()
  }
}

onMounted(async ()=>{
  await carregarCursos()
})

</script>

<style scoped>

.container {
  max-width: 800px;
  margin: 2rem auto;
  padding: 1rem;
  background-color: #ffffff;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.05);
  border-radius: 10px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  color: black;
}
</style>
