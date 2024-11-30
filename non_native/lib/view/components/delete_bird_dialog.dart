import 'package:flutter/material.dart';

import '../../model/bird.dart';

class DeleteBirdDialog extends StatelessWidget {
  final bool showDialog;
  final Bird? birdToDelete;
  //final Function(int) onDeleteConfirmed;
  final Future<void> Function(int) onDeleteConfirmed;
  final Function onDismiss;

  const DeleteBirdDialog({
    super.key,
    required this.showDialog,
    required this.birdToDelete,
    required this.onDeleteConfirmed,
    required this.onDismiss,
  });

  @override
  Widget build(BuildContext context) {
    return showDialog
        ? AlertDialog(
            title: const Text("Delete Bird"),
            content:
                Text("Are you sure you want to delete ${birdToDelete?.name}?"),
            actions: [
              TextButton(
                onPressed: () => onDismiss(),
                child: const Text("Cancel"),
              ),
              TextButton(
                onPressed: () async {
                  await onDeleteConfirmed(birdToDelete!.id);
                },
                child: const Text("Confirm"),
              ),
            ],
          )
        : const SizedBox.shrink();
  }
}
