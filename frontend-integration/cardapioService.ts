// services/cardapioService.ts
// Instale o Axios: npm install axios

import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
});

// ─── TIPOS ────────────────────────────────────────────────────────────────────

export type Categoria = 'ENTRADA' | 'PRATO_PRINCIPAL' | 'SOBREMESA' | 'BEBIDA' | 'LANCHE';

export interface PratoRequest {
  nome: string;
  descricao?: string;
  preco: number;
  categoria: Categoria;
  urlImagem?: string;
  disponivel?: boolean;
}

export interface PratoResponse {
  id: number;
  nome: string;
  descricao: string;
  preco: number;
  categoria: Categoria;
  urlImagem: string;
  disponivel: boolean;
  criadoEm: string;
  atualizadoEm: string;
}

// ─── SERVIÇOS ─────────────────────────────────────────────────────────────────

// GET todos os pratos
export const listarPratos = async (): Promise<PratoResponse[]> => {
  const { data } = await api.get<PratoResponse[]>('/pratos');
  return data;
};

// GET somente pratos disponíveis
export const listarPratosDisponiveis = async (): Promise<PratoResponse[]> => {
  const { data } = await api.get<PratoResponse[]>('/pratos/disponiveis');
  return data;
};

// GET prato por ID
export const buscarPrato = async (id: number): Promise<PratoResponse> => {
  const { data } = await api.get<PratoResponse>(`/pratos/${id}`);
  return data;
};

// GET pratos por categoria
export const listarPorCategoria = async (
  categoria: Categoria
): Promise<PratoResponse[]> => {
  const { data } = await api.get<PratoResponse[]>(`/pratos/categoria/${categoria}`);
  return data;
};

// POST criar prato
export const criarPrato = async (prato: PratoRequest): Promise<PratoResponse> => {
  const { data } = await api.post<PratoResponse>('/pratos', prato);
  return data;
};

// PUT atualizar prato
export const atualizarPrato = async (
  id: number,
  prato: PratoRequest
): Promise<PratoResponse> => {
  const { data } = await api.put<PratoResponse>(`/pratos/${id}`, prato);
  return data;
};

// DELETE remover prato
export const deletarPrato = async (id: number): Promise<void> => {
  await api.delete(`/pratos/${id}`);
};


// ─── EXEMPLO DE USO NO COMPONENTE ─────────────────────────────────────────────
/*
import { listarPratosDisponiveis, criarPrato } from '@/services/cardapioService';

// Listar
const pratos = await listarPratosDisponiveis();

// Criar
await criarPrato({
  nome: 'Frango Grelhado',
  descricao: 'Prato saudável com legumes',
  preco: 28.90,
  categoria: 'PRATO_PRINCIPAL',
  disponivel: true,
});
*/
