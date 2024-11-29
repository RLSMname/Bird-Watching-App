import 'package:flutter/material.dart';
import 'package:non_native/view/components/my_form.dart';

import '../../model/bird.dart';
import '../components/header.dart';

class AddEditScreen extends StatelessWidget {
  const AddEditScreen(
      {super.key,
      required this.headerText,
      required this.headerSubText,
      this.existingBird});

  final String headerText;
  final String headerSubText;
  final Bird? existingBird;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      body: Column(
        children: [
          Header(text: headerText, subText: headerSubText),
          const SizedBox(
            height: 30,
          ),
          //form
          Expanded(
            child: MyForm(
              existingBird: existingBird,
            ),
          ),
          //footer button
        ],
      ),
    );
  }
}
