import 'package:flutter/material.dart';
import 'package:non_native/model/habitat.dart';
import 'package:non_native/view/components/custom_medium_text.dart';

class HabitatDropDown extends StatelessWidget {
  const HabitatDropDown(
      {super.key, required this.onChanged, required this.value});

  final void Function(dynamic) onChanged;
  final Habitat value;

  @override
  Widget build(BuildContext context) {
    return DropdownButtonFormField(
        decoration: InputDecoration(
          focusedBorder: OutlineInputBorder(
            borderSide: const BorderSide(color: Colors.black),
            borderRadius: BorderRadius.circular(10),
          ),
          enabledBorder: const OutlineInputBorder(
            borderSide: BorderSide(
              color: Colors.black,
            ),
          ),
        ),
        value: value,
        items: Habitat.values.map((h) {
          return DropdownMenuItem(
            value: h,
            child: CustomMediumText(text: h.name.toUpperCase()),
          );
        }).toList(),
        onChanged: onChanged);
  }
}
