import '../model/bird.dart';

class BirdValidator {
  final int maxOrderLength = 16; // Phaethontiformes
  final int maxFamilyLength = 13; // Coraciiformes or Dromaiidae

  List<String> validate(Bird bird) {
    final List<String> errors = [];

    // Validate name
    if (bird.name.isEmpty) {
      errors.add("Name cannot be empty.");
    } else if (!RegExp(r'^[a-zA-Z\s]+$').hasMatch(bird.name)) {
      errors.add("Name must only contain letters and spaces.");
    }

    // Validate order
    if (bird.order.isEmpty) {
      errors.add("Order cannot be empty.");
    } else if (!RegExp(r'^[a-zA-Z]+$').hasMatch(bird.order)) {
      errors.add("Order must only contain letters, no spaces.");
    } else if (bird.order.length > maxOrderLength) {
      errors.add("Order must not exceed $maxOrderLength letters.");
    }

    // Validate family
    if (bird.family.isEmpty) {
      errors.add("Family cannot be empty.");
    } else if (!RegExp(r'^[a-zA-Z]+$').hasMatch(bird.family)) {
      errors.add("Family must only contain letters, no spaces.");
    } else if (bird.family.length > maxFamilyLength) {
      errors.add("Family must not exceed $maxFamilyLength letters.");
    }

    return errors;
  }
}
