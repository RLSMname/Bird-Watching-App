import 'package:flutter/material.dart';

class CustomMediumText extends StatelessWidget {
  final String text;
  final double size;
  final double lineHeight;
  final Color color;

  const CustomMediumText({
    Key? key,
    required this.text,
    this.size = 11.0,
    this.lineHeight = 23.0,
    this.color = Colors.black,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Text(
      text,
      style: TextStyle(
        color: color,
        fontWeight: FontWeight.w500,
        fontSize: size,
        height: lineHeight / size,
      ),
    );
  }
}
