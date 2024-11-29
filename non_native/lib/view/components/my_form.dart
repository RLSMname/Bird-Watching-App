import 'package:flutter/material.dart';
import 'package:non_native/model/bird_form_state.dart';
import 'package:non_native/model/habitat.dart';
import 'package:non_native/util/bird_validator.dart';
import 'package:non_native/view/components/custom_text_from_field.dart';
import 'package:non_native/view/components/sighting_counter.dart';

import '../../model/bird.dart';
import 'footer_button.dart';
import 'habitat_drop_down.dart';

class MyForm extends StatefulWidget {
  const MyForm({
    super.key,
    this.existingBird,
  });
  final Bird? existingBird;

  @override
  State<MyForm> createState() => _MyFormState();
}

class _MyFormState extends State<MyForm> {
  late BirdFormState _birdFormState;

  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();

  void _onSubmit() {
    if (_formKey.currentState!.validate()) {
      //this calls all the updates
      _formKey.currentState!.save();
      print(_birdFormState.stateToString());
    }
  }

  @override
  void initState() {
    super.initState();
    _birdFormState = BirdFormState(
      name: widget.existingBird?.name ?? '',
      order: widget.existingBird?.order ?? '',
      family: widget.existingBird?.family ?? '',
      habitat: widget.existingBird?.habitat ?? Habitat.forest,
      sightCount: widget.existingBird?.sightCount ?? 1,
    );
  }

  @override
  Widget build(BuildContext context) {
    return Form(
        key: _formKey,
        child: Column(
          children: [
            //common name
            Padding(
              padding: const EdgeInsets.only(top: 25, left: 25, right: 25),
              child: Column(
                children: [
                  CustomTextFormField(
                      initialValue: _birdFormState.name,
                      hintText: "Insert common name...",
                      validator: BirdValidator.validateName,
                      onSaved: (value) {
                        print("COMMON NAME FIELD: $value");
                        _birdFormState = _birdFormState.copyWith(name: value);
                      },
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
                          initialValue: _birdFormState.order,
                          labelText: "ORDER",
                          hintText: "Insert order...",
                          validator: BirdValidator.validateOrder,
                          onSaved: (value) {
                            print("ORDER FIELD: $value");
                            _birdFormState =
                                _birdFormState.copyWith(order: value);
                          },
                        ),
                      ),
                      const SizedBox(width: 15),
                      Expanded(
                        child: CustomTextFormField(
                          initialValue: _birdFormState.family,
                          labelText: "FAMILY",
                          hintText: "Insert family...",
                          validator: BirdValidator.validateFamily,
                          onSaved: (value) {
                            print("FAMILY FIELD: $value");
                            _birdFormState =
                                _birdFormState.copyWith(family: value);
                          },
                        ),
                      ),
                    ],
                  ),
                  const SizedBox(height: 15),
                  //habitat dropdown
                  HabitatDropDown(
                      onChanged: (value) {
                        _birdFormState =
                            _birdFormState.copyWith(habitat: value);
                      },
                      value: _birdFormState.habitat),
                  const SizedBox(height: 15),
                  //sightings counter (plus minus text between)
                  SightingCounter(
                      sightCount: _birdFormState.sightCount,
                      onValueChange: (value) {
                        setState(() {
                          _birdFormState =
                              _birdFormState.copyWith(sightCount: value);
                        });
                      }),
                ],
              ),
            ),

            const Spacer(),
            FooterButton(onPressed: _onSubmit, text: "ADD")
          ],
        ));
  }
}
