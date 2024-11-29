import 'package:flutter/material.dart';
import 'package:non_native/util/colors.dart';
import 'package:non_native/view/components/custom_white_text.dart';

class FooterButton extends StatelessWidget {
  const FooterButton({super.key, required this.onPressed, required this.text});

  final Function() onPressed;
  final String text;

  final Color _myColor = AppColors.lightGreen;

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      color: _myColor,
      child: FilledButton(
          style: FilledButton.styleFrom(backgroundColor: _myColor),
          onPressed: onPressed,
          child: CustomWhiteText(
            text: text,
            size: 40,
          )),
    );
  }
}
