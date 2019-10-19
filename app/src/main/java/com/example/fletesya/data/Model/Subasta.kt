package com.example.fletesya.data.Model

data class subastaListado (val data: List<Subasta>)

data class Subasta(val id_subasta: Int, val comuna_origen: String)

data class logoListado(val logos: List<Logo>)

data class Logo(val logo: String)