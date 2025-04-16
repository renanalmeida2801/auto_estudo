<template>
    <div class="container">
        <h1>Cadastrar Usuário</h1>

    <UserFormComponent
    :cursos="cursos"
    :usuario="usuarioSelecionado"
    :editando="editando"
    @cancelar="cancelarEdicao"
    @salvar="salvarUsuario"
    />

    <hr/>

    <h2>Usuários</h2>
    <UserTableComponent
    :usuarios="usuarios"
    @editar="editarUsuario"
    @excluir="excluirUsuario"
    />
</div>
</template>

<script lang="ts" setup>
import {ref, onMounted} from 'vue'
import UserFormComponent from '../components/form/UserFormComponent.vue'
import UserTableComponent from '../components/table/UserTableComponent.vue'

const usuarios = ref([])
const cursos = ref([])
const usuarioSelecionado = ref(null)
const editando = ref(false)

const API_URL = 'http://localhost:8080/usuarios'
const CURSOS_URL = 'http://localhost:8080/cursos'

const carregarUsuarios = async()=>{
    const response = await fetch(API_URL)
    usuarios.value = await response.json()
}

const carregarCursos = async()=>{
    const response = await fetch(CURSOS_URL)
    cursos.value = await response.json()
}

const salvarUsuario = async(usuario) =>{
    const url = editando.value? `${API_URL}/${usuario.id}` : API_URL
    const method = editando.value? 'PUT': 'POST'

    await fetch(url, {
        method,
        headers: {'Content-type': 'application/json'},
        body: JSON.stringify({
            nome: usuario.nome,
            email: usuario.email,
            curso: { id: usuario.curso }
        })
    })

    await carregarUsuarios()
    cancelarEdicao()
}

const editarUsuario = (usuario) => {
    usuarioSelecionado.value = { ...usuario, curso: usuario.curso?.id }
    editando.value = true
}

const cancelarEdicao = () => {
    usuarioSelecionado.value = null
    editando.value = false
}

const excluirUsuario = async (id) => {
    if (confirm('Tem certeza que deseja excluir?')) {
        await fetch(`${API_URL}/${id}`, { method: 'DELETE' })
        await carregarUsuarios()
    }
}

onMounted(async () => {
    await carregarUsuarios()
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
}</style>
