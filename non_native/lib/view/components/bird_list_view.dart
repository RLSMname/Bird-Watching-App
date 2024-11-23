import 'package:flutter/material.dart';

import '../../model/bird.dart';
import 'bird_card.dart';
import 'delete_bird_dialog.dart';
import 'loading_dialog.dart';

class BirdListView extends StatefulWidget {
  final List<Bird> birds;
  final bool isLoading;
  final Function(int) onDeleteBird;
  final Function(int) onEditBird;

  const BirdListView({
    super.key,
    required this.birds,
    required this.isLoading,
    required this.onDeleteBird,
    required this.onEditBird,
  });

  @override
  State<BirdListView> createState() => _BirdListViewState();
}

class _BirdListViewState extends State<BirdListView> {
  bool showDialog = false;
  Bird? birdToDelete;

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        if (widget.isLoading) const LoadingDialog(),

        Padding(
          padding: const EdgeInsets.symmetric(horizontal: 15.0),
          child: MediaQuery.removePadding(
            context: context,
            removeTop: true,
            child: ListView.separated(
              itemCount: widget.birds.length,
              itemBuilder: (context, index) {
                final bird = widget.birds[index];
                return BirdCard(
                  name: bird.name,
                  order: bird.order,
                  family: bird.family,
                  habitatTemp: bird.habitat.name,
                  number: bird.sightCount,
                  onEditClick: () => widget.onEditBird(bird.id),
                  onDeleteClick: () {
                    setState(() {
                      birdToDelete = bird;
                      showDialog = true;
                    });
                  },
                );
              },
              separatorBuilder: (context, index) {
                return const SizedBox(height: 30);
              },
            ),
          ),
        ),

        //confirmation dialog
        if (showDialog)
          DeleteBirdDialog(
            showDialog: showDialog,
            birdToDelete: birdToDelete,
            onDeleteConfirmed: (id) {
              widget.onDeleteBird(id);
              setState(() {
                showDialog = false;
              });
            },
            onDismiss: () {
              setState(() {
                showDialog = false;
              });
            },
          ),
      ],
    );
  }
}
