import 'package:flutter/material.dart';
import 'package:non_native/model/habitat.dart';
import 'package:non_native/view/components/header.dart';
import 'package:non_native/view/components/plus_button.dart';

import '../../model/bird.dart';
import '../components/bird_list_view.dart';

class MainScreen extends StatelessWidget {
  final String headerText = "Your collection";
  final String headerSubText = "ALWAYS READY FOR A NEW ADVENTURE";

  final void Function(int) onDeleteBird;
  final void Function() onClickPlusButton;
  final void Function(int) onEditBird;

  MainScreen({
    super.key,
    required this.onDeleteBird,
    required this.onClickPlusButton,
    required this.onEditBird,
  });

  final List<Bird> birds = [
    Bird(
        id: 1,
        name: 'Eagle',
        order: 'Accipitriformes',
        family: 'Accipitridae',
        habitat: Habitat.forest,
        sightCount: 5),
    Bird(
        id: 2,
        name: 'Sparrow',
        order: 'Passeriformes',
        family: 'Passeridae',
        habitat: Habitat.grassland,
        sightCount: 3),
    Bird(
        id: 3,
        name: 'Parrot',
        order: 'Psittaciformes',
        family: 'Psittacidae',
        habitat: Habitat.wetland,
        sightCount: 8),
    Bird(
        id: 4,
        name: 'Pigeon',
        order: 'Columbiformes',
        family: 'Columbidae',
        habitat: Habitat.forest,
        sightCount: 4),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          Header(text: headerText, subText: headerSubText),
          const SizedBox(
            height: 30,
          ),
          //plus button
          PlusButton(onClick: onClickPlusButton),
          //list
          const SizedBox(
            height: 20,
          ),
          Expanded(
            child: BirdListView(
              birds: birds,
              isLoading: false,
              onDeleteBird: onDeleteBird,
              onEditBird: onEditBird,
            ),
          ),
        ],
      ),
    );
  }
}
