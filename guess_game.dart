import 'dart:math';

import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class GuessGame extends StatefulWidget {
  const GuessGame({Key? key}) : super(key: key);

  @override
  State<GuessGame> createState() => _GuessGameState();
}

class _GuessGameState extends State<GuessGame> {
  final TextEditingController _tec = TextEditingController();

  @override
  void initState() {
    //print(random);
    print(context.read<GuessState>().random);
    super.initState();
  }

  void _guess() {
    var gameState = context.read<GuessState>();
    gameState.intentos++;
    var intento = int.tryParse(_tec.text);
    if (intento != null) {
      if (intento > gameState.random) {
        gameState.mensaje = "El número $intento es mayor";
      } else if (intento < gameState._random) {
        gameState.mensaje = "El número $intento es menor";
      } else {
        gameState.mensaje = "El número $intento es el número ganador";
        gameState.terminado = true;
      }
    }
  }

  void _startNewGame() {
    context.read<GuessState>().terminado = false;
    context.read<GuessState>().random = Random().nextInt(100);
    context.read<GuessState>().mensaje = "";
    context.read<GuessState>().intentos = 0;
    _tec.text = "";
    print("New Random:" + context.read<GuessState>().random.toString());
  }

  @override
  Widget build(BuildContext context) {
    var gameState = context.watch<GuessState>();
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          "Guess Game",
          textAlign: TextAlign.center,
        ),
        backgroundColor: Colors.red,
      ),
      body: Padding(
        padding: const EdgeInsets.all(14.0),
        child: Column(mainAxisAlignment: MainAxisAlignment.center, children: [
          const Text(
            "Ingresa un numero entre 0 y 100",
            style: TextStyle(
                color: Colors.red, fontSize: 20, fontWeight: FontWeight.bold),
          ),
          TextField(
            controller: _tec,
            enabled: !gameState._terminado,
          ),
          SizedBox(
              width: double.infinity,
              child: ElevatedButton(
                  onPressed: gameState._terminado ? null : _guess,
                  child: const Text("Jugar"))),
          const SizedBox(
            height: 10,
          ),
          SizedBox(
            width: double.infinity,
            child: Text(
              "Mensaje: " + gameState._mensaje,
              style: TextStyle(
                  color: gameState._terminado ? Colors.green : Colors.black),
              textAlign: TextAlign.left,
            ),
          ),
          SizedBox(
              width: double.infinity,
              child: Text("Intentos: " + gameState._intentos.toString()))
        ]),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _startNewGame,
        child: const Icon(Icons.refresh),
      ),
    );
  }
}

class GuessState extends ChangeNotifier {
  int _random = Random().nextInt(100);
  String _mensaje = "";
  int _intentos = 0;
  bool _terminado = false;

  String get mensaje => _mensaje;
  set mensaje(String mensaje) {
    _mensaje = mensaje;
    notifyListeners();
  }

  int get intentos => _intentos;
  set intentos(int intentos) {
    _intentos = intentos;
    notifyListeners();
  }

  bool get terminado => _terminado;
  set terminado(bool terminado) {
    _terminado = terminado;
    notifyListeners();
  }

  int get random => _random;
  set random(int random) {
    _random = random;
    notifyListeners();
  }
}
