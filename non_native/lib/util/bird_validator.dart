import '../model/bird.dart';

class BirdValidator {
  static const int _maxOrderLength = 16; // Phaethontiformes
  static const int _maxFamilyLength = 13; // Coraciiformes or Dromaiidae

  static List<String> validate(Bird bird) {
    final List<String> errors = [];

    String? name = validateName(bird.name);
    String? order = validateOrder(bird.order);
    String? family = validateFamily(bird.family);

    if (name != null) errors.add(name);
    if (order != null) errors.add(order);
    if (family != null) errors.add(family);

    return errors;
  }

  static String? validateName(String? name) {
    if (name == null) return "Name cannot be empty.";
    if (name.isEmpty) {
      return "Name cannot be empty.";
    } else if (!RegExp(r'^[a-zA-Z\s]+$').hasMatch(name)) {
      return "Only letters and spaces.";
    }
    return null;
  }

  static String? validateOrder(String? order) {
    if (order == null) return "Order cannot be empty.";
    if (order.isEmpty) {
      return "Order cannot be empty.";
    } else if (!RegExp(r'^[a-zA-Z]+$').hasMatch(order)) {
      return "Only letters.";
    } else if (order.length > _maxOrderLength) {
      return "Less than ${_maxOrderLength + 1} letters.";
    }
    return null;
  }

  static String? validateFamily(String? family) {
    if (family == null) return "Family cannot be empty.";
    if (family.isEmpty) {
      return "Family cannot be empty.";
    } else if (!RegExp(r'^[a-zA-Z]+$').hasMatch(family)) {
      return "Only letters.";
    } else if (family.length > _maxFamilyLength) {
      return "Less than ${_maxFamilyLength + 1} letters.";
    }
    return null;
  }
}
