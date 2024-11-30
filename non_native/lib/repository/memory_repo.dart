import '../model/bird.dart';
import '../model/habitat.dart';

//class similar to the ones used later for db and server for consistency

class MemoryRepo {
  final List<Bird> _birds = [
    Bird(
        id: 1,
        name: 'Eagle',
        order: 'Accipitriformes',
        family: 'Accipitridae',
        habitat: Habitat.forest,
        sightCount: 5),
    Bird(
        id: 2,
        name: 'Sparrow',
        order: 'Passeriformes',
        family: 'Passeridae',
        habitat: Habitat.grassland,
        sightCount: 3),
    Bird(
        id: 3,
        name: 'Parrot',
        order: 'Psittaciformes',
        family: 'Psittacidae',
        habitat: Habitat.wetland,
        sightCount: 8),
    Bird(
        id: 4,
        name: 'Pigeon',
        order: 'Columbiformes',
        family: 'Columbidae',
        habitat: Habitat.forest,
        sightCount: 4),
  ];
  int _nextId = 10;

  Future<List<Bird>> fetchAll() async {
    return _birds;
  }
}
