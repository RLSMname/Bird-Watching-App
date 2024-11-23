import 'package:flutter/material.dart';

import '../../util/colors.dart';
import 'custom_white_text.dart';

class Header extends StatelessWidget {
  final String text;
  final String subText;
  final double headerSize;

  const Header({
    Key? key,
    required this.text,
    required this.subText,
    this.headerSize = 40.0,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.center,
      mainAxisAlignment: MainAxisAlignment.end,
      children: [
        Container(
          width: double.infinity,
          color: AppColors.mediumOrange,
          padding: const EdgeInsets.all(25.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              CustomWhiteText(text: text, size: headerSize),
              const SizedBox(height: 10),
              CustomWhiteText(text: subText),
            ],
          ),
        ),
      ],
    );
  }
}
