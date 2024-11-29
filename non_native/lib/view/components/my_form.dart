import 'package:flutter/material.dart';
import 'package:non_native/view/components/custom_text_from_field.dart';

import '../../model/habitat.dart';
import 'habitat_drop_down.dart';

class MyForm extends StatefulWidget {
  const MyForm({Key? key}) : super(key: key);

  @override
  _MyFormState createState() => _MyFormState();
}

class _MyFormState extends State<MyForm> {
  String? placeholderValidator(String? value) {
    return null;
  }

  void placeholderOnSaved(String? value) {}

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(top: 25, left: 25, right: 25),
      child: Form(
          child: Column(
        children: [
          //common name
          CustomTextFormField(
              hintText: "Insert common name...",
              validator: placeholderValidator,
              onSaved: placeholderOnSaved,
              labelText: "COMMON NAME"),
          const SizedBox(
            height: 15,
          ),
          //row
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Expanded(
                child: CustomTextFormField(
                  labelText: "ORDER",
                  hintText: "Insert order...",
                  validator: placeholderValidator,
                  onSaved: placeholderOnSaved,
                ),
              ),
              const SizedBox(width: 15),
              Expanded(
                child: CustomTextFormField(
                  labelText: "FAMILY",
                  hintText: "Insert family...",
                  validator: placeholderValidator,
                  onSaved: placeholderOnSaved,
                ),
              ),
            ],
          ),
          const SizedBox(height: 15),
          //habitat dropdown
          HabitatDropDown(onChanged: (something) {}, value: Habitat.forest),
          //sightings counter (plus minus text between)

          const SizedBox(height: 15),
          Container(
            color: Colors.blueGrey,
            child: const Text("MISSING COMPONENT"),
          )
        ],
      )),
    );
  }
}
