import 'package:flutter/material.dart';
import 'package:non_native/view/components/custom_medium_text.dart';

class CustomTextFormField extends StatelessWidget {
  final String? Function(String?) validator;
  final void Function(String?) onSaved;
  final String labelText;
  final String hintText;

  const CustomTextFormField(
      {super.key,
      required this.validator,
      required this.onSaved,
      required this.labelText,
      required this.hintText});

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        CustomMediumText(
          text: labelText,
          size: 12,
        ),
        TextFormField(
          onSaved: onSaved,
          validator: validator,
          decoration: InputDecoration(
            hintText: hintText,
            hintStyle: const TextStyle(color: Colors.grey),
            focusedBorder: OutlineInputBorder(
              borderSide: const BorderSide(color: Colors.transparent),
              borderRadius: BorderRadius.circular(10),
            ),
            enabledBorder: OutlineInputBorder(
              borderSide: const BorderSide(color: Colors.transparent),
              borderRadius: BorderRadius.circular(10),
            ),
            filled: true,
            fillColor: Colors.grey[200],
          ),
        ),
      ],
    );
  }
}
