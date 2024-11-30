import 'package:flutter/material.dart';
import 'package:non_native/model/bird_form_state.dart';
import 'package:provider/provider.dart';

import '../../model/bird.dart';
import '../../viewmodel/bird_view_model.dart';
import '../components/header.dart';
import '../components/my_form.dart';

class AddEditScreenArguments {
  final String headerText;
  final String headerSubText;

  //final Bird? existingBird;
  final int? birdId;

  AddEditScreenArguments(
      {required this.headerText,
      required this.headerSubText,
      //required this.existingBird
      required this.birdId});
}

class AddEditScreen extends StatelessWidget {
  const AddEditScreen({
    super.key,
  });

  /*
  required this.headerText,
      required this.headerSubText,
      this.existingBird

   */

  Future<Bird?> _addBird(
      BirdFormState state, BirdViewModel birdViewModel, int? id) async {
    final bird = Bird(
        id: -1,
        name: state.name,
        order: state.order,
        family: state.family,
        habitat: state.habitat,
        sightCount: state.sightCount);
    return await birdViewModel.addBird(bird);
  }

  Future<Bird?> _updateBird(
      BirdFormState state, BirdViewModel birdViewModel, int? id) async {
    final bird = Bird(
        id: id!,
        name: state.name,
        order: state.order,
        family: state.family,
        habitat: state.habitat,
        sightCount: state.sightCount);
    return await birdViewModel.updateBird(bird);
  }

  @override
  Widget build(BuildContext context) {
    final args =
        ModalRoute.of(context)?.settings.arguments as AddEditScreenArguments;

    final birdViewModel = Provider.of<BirdViewModel>(context);
    return Scaffold(
      resizeToAvoidBottomInset: false,
      body: Column(
        children: [
          Header(text: args.headerText, subText: args.headerSubText),
          const SizedBox(
            height: 30,
          ),

          Expanded(
              child: args.birdId == null
                  ? MyForm(
                      existingBird: null,
                      buttonText: "ADD",
                      onSubmit: (state) async {
                        return await _addBird(state, birdViewModel, null);
                      },
                    )
                  : FutureBuilder(
                      future: birdViewModel.fetchBirdById(args.birdId!),
                      builder: (context, snapshot) {
                        if (snapshot.hasData) {
                          final existingBird = snapshot.data;
                          return MyForm(
                            existingBird: existingBird,
                            buttonText: "EDIT",
                            onSubmit: (state) async {
                              print("MYFORM SUBMIT UPDATE");
                              return await _updateBird(
                                  state, birdViewModel, args.birdId);
                            },
                          );
                        } else if (snapshot.hasError) {
                          return Center(
                            child: Text('Error: ${snapshot.error}'),
                          );
                        } else {
                          return const Center(
                              child: CircularProgressIndicator());
                        }
                      }))

          //footer button
        ],
      ),
    );
  }
}
