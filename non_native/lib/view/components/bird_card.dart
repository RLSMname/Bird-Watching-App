import 'package:flutter/material.dart';

import '../../util/colors.dart';
import 'custom_button.dart';
import 'custom_white_text.dart';

class BirdCard extends StatefulWidget {
  final String name;
  final String order;
  final String family;
  final String habitatTemp;
  final int number;
  final VoidCallback onEditClick;
  final VoidCallback onDeleteClick;

  const BirdCard({
    super.key,
    required this.name,
    required this.order,
    required this.family,
    required this.habitatTemp,
    required this.number,
    required this.onEditClick,
    required this.onDeleteClick,
  });

  @override
  State<BirdCard> createState() => _BirdCardState();
}

class _BirdCardState extends State<BirdCard> {
  bool enabledButtons = true;

  @override
  Widget build(BuildContext context) {
    Color myColor = AppColors.darkGreen;
    Color myEditColor = AppColors.lightGreen;
    Color myDeleteColor = AppColors.darkOrange;

    return Card(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(20),
      ),
      color: myColor,
      child: Padding(
        padding: const EdgeInsets.all(20.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            CustomWhiteText(text: widget.name, size: 24),
            const SizedBox(
              height: 12,
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                CustomWhiteText(text: widget.order),
                const SizedBox(width: 5),
                CustomWhiteText(text: widget.family),
              ],
            ),
            const SizedBox(
              height: 12,
            ),
            CustomWhiteText(text: widget.habitatTemp.toUpperCase()),
            const SizedBox(
              height: 12,
            ),
            CustomWhiteText(text: "Seen ${widget.number} time(s)", size: 12),
            const SizedBox(
              height: 12,
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Expanded(
                  child: CustomButton(
                    text: "EDIT",
                    backGroundColor: myEditColor,
                    enabled: enabledButtons,
                    onClick: () {
                      setState(() {
                        enabledButtons = false;
                      });
                      widget.onEditClick();
                    },
                  ),
                ),
                const SizedBox(width: 5),
                Expanded(
                  child: CustomButton(
                    text: "DELETE",
                    backGroundColor: myDeleteColor,
                    enabled: enabledButtons,
                    onClick: widget.onDeleteClick,
                  ),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
