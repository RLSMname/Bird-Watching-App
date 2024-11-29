import 'package:flutter/material.dart';
import 'package:non_native/view/components/custom_medium_text.dart';

class SightingCounter extends StatelessWidget {
  final int sightCount;
  final ValueChanged<int> onValueChange;

  const SightingCounter({
    Key? key,
    required this.sightCount,
    required this.onValueChange,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.center,
      children: [
        const CustomMediumText(
            text: "NUMBER OF TIMES YOU’VE SEEN THIS BIRD", size: 11),
        const SizedBox(height: 16),
        Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            CounterButton(
              onClick: () {
                if (sightCount > 1) onValueChange(sightCount - 1);
              },
              text: "−",
              enabled: sightCount > 1,
            ),
            const SizedBox(width: 24),
            Text(
              sightCount.toString(),
              style: const TextStyle(
                fontSize: 24,
                fontWeight: FontWeight.bold,
                color: Colors.black,
              ),
            ),
            const SizedBox(width: 24),
            CounterButton(
              onClick: () {
                onValueChange(sightCount + 1);
              },
              text: "+",
            ),
          ],
        ),
      ],
    );
  }
}

class CounterButton extends StatelessWidget {
  final VoidCallback onClick;
  final String text;
  final bool enabled;

  const CounterButton({
    super.key,
    required this.onClick,
    required this.text,
    this.enabled = true,
  });

  @override
  Widget build(BuildContext context) {
    return ElevatedButton(
      onPressed: enabled ? onClick : null,
      style: ElevatedButton.styleFrom(
        foregroundColor: enabled ? Colors.black : Colors.grey,
        backgroundColor: enabled ? Colors.white : Colors.grey.shade300,
        shape: const CircleBorder(), // Text color
        side: const BorderSide(color: Colors.black),
        padding: EdgeInsets.zero,
        minimumSize: const Size(40, 40), // Button size
      ),
      child: Text(
        text,
        style: TextStyle(
          fontSize: 24,
          fontWeight: FontWeight.bold,
          color: enabled ? Colors.black : Colors.grey,
        ),
      ),
    );
  }
}
