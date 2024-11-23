import 'package:flutter/material.dart';

import '../../util/colors.dart';

class PlusButton extends StatelessWidget {
  final Color backgroundColor;
  final String contentDescription;
  final bool enabled;
  final VoidCallback onClick;
  final double height;
  final double width;

  const PlusButton({
    super.key,
    this.backgroundColor = AppColors.mediumOrange,
    this.contentDescription = "Add",
    this.enabled = true,
    required this.onClick,
    this.height = 35.0,
    this.width = 140.0,
  });

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: height,
      width: width,
      child: ElevatedButton(
        style: ElevatedButton.styleFrom(
          backgroundColor: backgroundColor,
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(50),
          ),
        ),
        onPressed: enabled ? onClick : null, //disables the button
        child: const Icon(
          Icons.add_circle_outline,
          color: AppColors.white,
          size: 24.0,
        ),
      ),
    );
  }
}
