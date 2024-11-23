import 'habitat.dart';

class Bird {
  final int id;
  final String name;
  final String order;
  final String family;
  final Habitat habitat;
  final int sightCount;

  Bird({
    required this.id,
    required this.name,
    required this.order,
    required this.family,
    required this.habitat,
    required this.sightCount,
  });

  factory Bird.fromJson(Map<String, dynamic> json) {
    return Bird(
      id: json['id'] as int,
      name: json['name'] as String,
      order: json['order'] as String,
      family: json['family'] as String,
      habitat: HabitatExtension.fromString(json['habitat'] as String),
      sightCount: json['sightCount'] as int,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'name': name,
      'order': order,
      'family': family,
      'habitat': habitat.toJson(),
      'sightCount': sightCount,
    };
  }
}
