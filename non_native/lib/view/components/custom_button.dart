import 'package:flutter/material.dart';

class CustomButton extends StatelessWidget {
  final String text;
  final Color backGroundColor;
  final bool enabled;
  final VoidCallback onClick;
  final EdgeInsetsGeometry padding;
  final double fontSize;

  const CustomButton({
    super.key,
    required this.text,
    required this.backGroundColor,
    this.enabled = true,
    required this.onClick,
    this.padding = const EdgeInsets.symmetric(vertical: 12.0),
    this.fontSize = 20.0,
  });

  @override
  Widget build(BuildContext context) {
    return ElevatedButton(
      onPressed: enabled ? onClick : null,
      style: ElevatedButton.styleFrom(
        backgroundColor: backGroundColor,
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(10.0),
        ),
        padding: padding,
      ),
      child: Text(
        text,
        style: TextStyle(
          color: Colors.white,
          fontSize: fontSize,
        ),
      ),
    );
  }
}
