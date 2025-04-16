<template>
    <form @submit.prevent="onSubmit" class="form-container">
        <input v-model="cursoLocal.nome" placeholder="Nome" required/>
        <input v-model="cursoLocal.sigla" placeholder="Sigla" required/>
        <input v-model="cursoLocal.turno" placeholder="Turno" required/>
        <button type="submit">{{editando? 'Atualizar Curso': 'Cadastrar Curso' }}</button>
        <button v-if="editando" type="button" @click="$emit('cancelar')" id="cancelBtn">Cancelar</button>
    </form>
</template>

<script lang="ts" setup>
import {ref, watch} from 'vue'

const props = defineProps(['curso', 'editando'])
const emit= defineEmits(['salvar', 'cancelar'])

const cursoLocal = ref({nome:'', sigla:'', turno:''})

watch(
  ()=> props.curso,
  (novo) => {
    cursoLocal.value = novo ? {...novo}: {nome:'', sigla: '', turno:''}
  },
  {immediate:true}
)

const onSubmit = ()=>{
  emit('salvar', cursoLocal.value)
  cursoLocal.value = {nome:'', sigla:'', turno: ''}
}
</script>

<style scoped>
form {
    margin-bottom: 20px;
    display: flex;
    flex-direction: column;
    gap: 12px;
}

input {
    padding: 12px;
    border: 1px solid #ccc;
    border-radius: 6px;
    font-size: 1rem;
    transition: border-color 0.3s;
}

input:focus {
    border-color: #16b61e;
    outline: none;
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

.select-curso{
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    background-color: white;
    font-size: 1rem;
    color: #333;
    appearance: none;
    background-image: url("data:image/svg+xml;charset=US-ASCII,%3Csvg%20xmlns='http://www.w3.org/2000/svg'%20viewBox='0%200%204%205'%3E%3Cpath%20fill='%23666'%20d='M2%200L0%202h4L2%200zM2%205L0%203h4l-2%202z'/%3E");
    background-repeat: no-repeat;
    background-position: right 10px center;
    background-size: 8px 10px;
}

button:hover {
    background-color: #128e18;
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

