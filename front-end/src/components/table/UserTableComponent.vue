<template>
    <table>
        <thead>
            <tr>
                <th>Nome</th>
                <th>Email</th>
                <th>Curso</th>
                <th>Ações</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="usuario in usuarios" :key="usuario.id">
                <td>{{ usuario.nome }}</td>
                <td>{{ usuario.email }}</td>
            <td>{{ usuario.curso.nome }}</td>
            <td>
                <button id="editBtn" @click="$emit('editar', usuario)">Editar</button>
                <button id="deleteBtn" @click="$emit('excluir', usuario.id)">Excluir</button>
            </td>
            </tr>
        </tbody>
    </table>
</template>

<script lang="ts" setup>
defineProps(['usuarios'])
defineEmits(['editar', 'excluir'])
</script>

<style scoped>

table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 1.5rem;
    font-size: 0.95rem;
    box-shadow: 0 2px 6px rgba(0, 0, 0, 0.05);
    border-radius: 8px;
    overflow: hidden;
}

thead {
    background-color: #f5f5f5;
}

th, td {
    padding: 14px 18px;
    text-align: left;
    border-bottom: 1px solid #bfbdbd;
}

tbody tr:nth-child(even) {
    background-color: #fafafa;
}

tbody tr:hover {
    background-color: #f1faff;
}
button:hover {
    background-color: #128e18;
}

button {
    padding: 0.6rem 1.2rem;
    background-color: #16b61e;
    border: none;
    border-radius: 6px;
    color: white;
    font-weight: 600;
    cursor: pointer;
    transition: background-color 0.3s ease;
    margin: 2px;
}

#cancelBtn,
#deleteBtn {
    background-color: #e53935;
}

#cancelBtn:hover,
#deleteBtn:hover {
    background-color: #c62828;
}

#editBtn {
    background-color: #00bcd4;
    color: white;
}

#editBtn:hover {
    background-color: #00acc1;
}

</style>
