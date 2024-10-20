The "Bird Watcher" app is a user-friendly tool designed for bird enthusiasts to effortlessly track and enjoy their bird sightings. Whether you’re a seasoned expert or just starting out, the app provides an easy way to manage your favorite birds and keep a record of your observations. With a straightforward list format, users can quickly view and update their saved birds, making the experience enjoyable and stress-free.

The bird will have:
a) A name that will represent the common name the bird can be found by
b) An order to provide insight into its biological grouping
c) A family classification, helping users understand relationships between species
d) A habitat it is commonly found in (e.g., Forest, Wetland, Grassland)
e) A number of sightings, allowing users to keep track of how often they have personally seen this bird

Users can add new birds to their collection by completing a small form, they will however not be allowed to add birds they have not already seen.
In case the fields were not correct, or the user has seen the bird again, updating the details of the bird is possible using a similar form, one click away.
Users can view a list of all birds they have sighted, along with detailed information for each bird, including its habitat and classification.
Users can remove a bird from their records if they no longer wish to track it.

Local DB Operations:

Create: New birds are stored in the local database immediately for quick access.
Read: Users can view saved birds from the local database.
Update: Changes to birds are reflected in the local database.
Delete: Birds can be removed from the local database.

Server Operations:

All the operations work on the bird collection of a single user.
Create: New birds will also be added to the server storage.
Read: Users can retrieve their bird list from the server when online.
Update: Updates are sent to the server to ensure that the changes will not be lost.
Delete: Deletions are communicated to the server to maintain the correctness of the data.
In summary, the local database stores a copy of the data for quicker access, while the server ensures that information is preserved in case a user loses it or switches to a new Android device.

When the app is used offline, the bird list is obtained from the local database.
Updates to the data like adding, changing the details of a bird or removing it completely, are also maintained locally so the user can use the application without noticing a difference.
When the device goes back online, the local database is synchronized with the server, by sending local updates.
