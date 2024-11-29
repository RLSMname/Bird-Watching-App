import 'package:flutter/material.dart';
import 'package:non_native/view/components/footer_button.dart';
import 'package:non_native/view/components/my_form.dart';

import '../components/header.dart';

class AddEditScreen extends StatelessWidget {
  const AddEditScreen({super.key});

  final String headerText = "Placeholder";
  final String headerSubText = "Placeholder";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          Header(text: headerText, subText: headerSubText),
          const SizedBox(
            height: 30,
          ),
          //form
          const Expanded(
            flex: 4,
            child: MyForm(),
          ),
          //footer button
          Expanded(flex: 1, child: FooterButton(onPressed: () {}, text: "ADD"))
        ],
      ),
    );
  }
}
