package br.com.systemsgs

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder

fun main() {

    val channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build()

    val request = FuncionarioRequest.newBuilder()
        .setNome("Guilherme")
        .setCpf("000.000.000-00")
        .setIdade(22)
        .setSalario(10000.00)
        .setAtivo(true)
        .setCargo(Cargo.DEV)
        .addEnderecos(FuncionarioRequest.Endereco.newBuilder()
            .setLogradouro("Rua dos Devs")
            .setCep("00000-000")
            .setComplemento("Casa 20")
            .build())
        .build()

    val client = FuncionarioServiceGrpc.newBlockingStub(channel)
    val response =  client.cadastrar(request)

    println(response)
}