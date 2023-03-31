import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(MyWidget());
}

class MyWidget extends StatefulWidget {
  const MyWidget({super.key});

  @override
  State<MyWidget> createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  TextEditingController Qtd_Pessoas = TextEditingController();
  TextEditingController ValorTotal = TextEditingController();
  TextEditingController Porcentagem_comissao = TextEditingController();
  String string_valortotal = "Conta a ser paga: ";
  String string_valorporpessoa = "Valor por pessoa: ";
  String string_valorgarcom = "Comissao: ";

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Scaffold(
            appBar: AppBar(
              title: Text("Divide conta"),
              centerTitle: true,
              backgroundColor: Color.fromARGB(255, 194, 16, 16),
            ),
            body: body()));
  }

  body() {
    return Padding(
        padding: EdgeInsets.all(15),
        child: Column(children: [
          Padding(
            padding: EdgeInsets.only(top: 50),
            child: TextField(
              decoration: InputDecoration(labelText: "Quantidade de pessoas"),
              keyboardType: TextInputType.number,
              controller: Qtd_Pessoas,
            ),
          ),
          Padding(
              padding: EdgeInsets.only(top: 50),
              child: TextField(
                decoration: InputDecoration(labelText: "Valor total da conta"),
                keyboardType: TextInputType.number,
                controller: ValorTotal,
              )),
          Padding(
              padding: EdgeInsets.only(top: 50),
              child: TextField(
                decoration:
                    InputDecoration(labelText: "Porcentagem da comissão"),
                keyboardType: TextInputType.number,
                controller: Porcentagem_comissao,
              )),
          Padding(
              padding: EdgeInsets.only(top: 50),
              child: ElevatedButton(
                style: ElevatedButton.styleFrom(
                  backgroundColor: Color.fromARGB(255, 0, 0, 0),
                  minimumSize: Size(200, 100),
                  maximumSize: Size(400, 200),
                ),
                onPressed: calcular,
                child: Text("CALCULAR"),
              )),
          Text(string_valortotal),
          Text(string_valorporpessoa),
          Text(string_valorgarcom)
        ]));
  }

  calcular() {
    
    double n_pessoas = double.parse(Qtd_Pessoas.text);
    double comissao = double.parse(Porcentagem_comissao.text);
    double vt = double.parse(ValorTotal.text) + (double.parse(ValorTotal.text)*(comissao/100));
    double Valor_por_pessoa = vt/n_pessoas;
    setState(() {
      string_valorporpessoa = "Valor por pessoa: " + Valor_por_pessoa.toStringAsPrecision(4);
      string_valortotal = "Conta a ser paga: " + vt.toStringAsPrecision(4);
      string_valorgarcom = "Comissao: " + (double.parse(ValorTotal.text)*(comissao/100)).toStringAsPrecision(4);
    });

    print(string_valorporpessoa);
    print(string_valortotal);
    print(string_valorgarcom);
  }
}
