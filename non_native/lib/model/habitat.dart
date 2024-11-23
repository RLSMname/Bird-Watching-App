enum Habitat { forest, grassland, wetland }

extension HabitatExtension on Habitat {
  static Habitat fromString(String habitat) {
    switch (habitat.toLowerCase()) {
      case 'forest':
        return Habitat.forest;
      case 'grassland':
        return Habitat.grassland;
      case 'wetland':
        return Habitat.wetland;
      default:
        throw ArgumentError('Unknown habitat: $habitat');
    }
  }

  String toJson() {
    return toString().split('.').last.toLowerCase();
  }
}
