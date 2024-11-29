import 'habitat.dart';

class BirdFormState {
  String name;
  String order;
  String family;
  Habitat habitat;
  int sightCount;

  BirdFormState({
    required this.name,
    required this.order,
    required this.family,
    required this.habitat,
    required this.sightCount,
  });
}

extension BirdFormStateCopyWith on BirdFormState {
  BirdFormState copyWith({
    String? name,
    String? order,
    String? family,
    Habitat? habitat,
    int? sightCount,
  }) {
    return BirdFormState(
      name: name ?? this.name,
      order: order ?? this.order,
      family: family ?? this.family,
      habitat: habitat ?? this.habitat,
      sightCount: sightCount ?? this.sightCount,
    );
  }
}

extension BirdFormStateString on BirdFormState {
  String stateToString() {
    return "Bird state:\nName=$name\nOrder=$order\nFamily=$family\nHabitat=${habitat.name}\nSightings=$sightCount";
  }
}
