import 'package:flutter/material.dart';
import 'package:non_native/view/components/my_form.dart';

import '../../model/bird.dart';
import '../components/header.dart';

class AddEditScreenArguments {
  final String headerText;
  final String headerSubText;
  final Bird? existingBird;

  AddEditScreenArguments(
      {required this.headerText,
      required this.headerSubText,
      required this.existingBird});
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

  @override
  Widget build(BuildContext context) {
    final args =
        ModalRoute.of(context)?.settings.arguments as AddEditScreenArguments;
    return Scaffold(
      resizeToAvoidBottomInset: false,
      body: Column(
        children: [
          Header(text: args.headerText, subText: args.headerSubText),
          const SizedBox(
            height: 30,
          ),
          //form
          Expanded(
            child: MyForm(
              existingBird: args.existingBird,
            ),
          ),
          //footer button
        ],
      ),
    );
  }
}
