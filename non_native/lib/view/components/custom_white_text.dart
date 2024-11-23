import 'package:flutter/material.dart';

class CustomWhiteText extends StatelessWidget {
  final String text;
  final double size;

  const CustomWhiteText({
    Key? key,
    required this.text,
    this.size = 14.0,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Text(
      text,
      style: TextStyle(
        color: Colors.white,
        fontWeight: FontWeight.bold,
        fontSize: size,
      ),
    );
  }
}
