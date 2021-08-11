package br.com.systemsgs

import com.google.protobuf.Timestamp
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver
import java.time.LocalDateTime
import java.time.ZoneId

fun main() {

    val server = ServerBuilder.forPort(50051).addService(FuncionarioEndpoint()).build()

    server.start()
    server.awaitTermination()

}

/*Cadastrando*/
class FuncionarioEndpoint : FuncionarioServiceGrpc.FuncionarioServiceImplBase(){

    /*Dados que vão ser recebidos la no BloomRPC e retornar o Nome e da Data de criação*/
    override fun cadastrar(request: FuncionarioRequest?, responseObserver: StreamObserver<FuncionarioResponse>?) {

        println(request!!)

        var nome: String? = request.nome

        if(!request.hasField(FuncionarioRequest.getDescriptor().findFieldByName("nome"))){
            nome = "[Campo Nome Vazio]"
        }

        val instant = LocalDateTime.now().atZone(ZoneId.of("UTC")).toInstant()
        val criadoEm = Timestamp.newBuilder().setSeconds(instant.epochSecond).setNanos(instant.nano).build()

        val response = FuncionarioResponse.newBuilder()
            .setNome(nome)
            .setCriadoEm(criadoEm)
            .build()

        responseObserver?.onNext(response)
        responseObserver?.onCompleted()

    }

}