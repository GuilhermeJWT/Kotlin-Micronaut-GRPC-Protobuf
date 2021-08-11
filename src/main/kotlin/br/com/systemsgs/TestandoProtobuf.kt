package br.com.systemsgs

import java.io.FileInputStream
import java.io.FileOutputStream

fun main() {

    val request = FuncionarioRequest.newBuilder()
        .setNome("Guilherme")
        .setCpf("000.000.000-00")
        .setSalario(10000.00)
        .setAtivo(true)
        .setCargo(Cargo.DEV)
        .addEnderecos(FuncionarioRequest.Endereco.newBuilder()
            .setLogradouro("Rua dos Devs")
            .setCep("00000-000")
            .setComplemento("Casa 20")
            .build())
        .build()

    println(request)
    request.writeTo(FileOutputStream("funcionario-request.bin"))

    val request2 = FuncionarioRequest.newBuilder().mergeFrom(FileInputStream("funcionario-request.bin"))
    request2.setCargo(Cargo.GERENTE).build()

    println(request2)

}