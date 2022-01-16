# JavaEZ
JavaEZ, making Java simple for newcomers.
JavaEZ is a library that adds new functions to make Java easier, hence the name. Please go to our wiki for instructions on how to use this.
# Installation
Please check out _[Startup Guide](https://github.com/RedstoneBoy0509/JavaEZ/wiki/Startup-Guide)_ for installation instructions.
# Our Goal
We try to make programming easier to newcomers so we can have a larger programming community
# Features Coming Soon
- Networking
- Advanced Files
# Contributions
To contribute, create a pull request and get started on your own branch! We would love to see user contributions.
Note: all legitimate commits past the date of 5/24/2021 will have a GPG signature and will display as verified.
# License changes
We have recently changed our license from the GNU General Public License v3 to the MIT license. Versions 1.0, 1.1 and 1.2 will REMAIN under GNU licensing while any releases coming out at or after 6/1/2021 will be licensed under the MIT license.
# Transfer
On June 1, 2021 the JavaEZ repository was transferred to the JavaEZLib account. This is to avoid confusion and, yes the repo & lib are still owned by me. JavaEZLib is an alt made for upholding this project. I still manage JavaEZ and own it.
# Server Location Changes
The Update Server has changed to a different link on 1/15/2022, and you may see errors while attempting to fetch version data from versions 1.0, 1.1, 1.2, and 1.3. Until 1.4 is released, a workaround is possible. Use this script below to check for updates until 1.4 comes out.
```java
public static String getLatestVersion() {
        List<String> versions = getVersions();
        return versions.isEmpty() ? "Unknown" : versions.get(versions.size() - 1);
}

public static List<String> getVersions() {
        try {
            URL url = new URL("https://gist.githubusercontent.com/Red050911/fb10258f9ae7d858f94b8cbaa651548f/raw/");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            List<String> list = new ArrayList<>();
            String line;
            while((line = reader.readLine()) != null) {
                if(line.isEmpty()) continue;
                list.add(line);
            }
            reader.close();
            return list;
        } catch(Exception ex) {
            return new ArrayList<>();
        }
}
```
And you can also find a replacement JavaEZ.info() here (make sure it is in the same class as the code above):
```java
public static void info() {
        System.out.println("=[JavaEZ Info]=");
        System.out.println("JavaEZ running on version " + JavaEZ.VERSION);
        String latestVersion = getLatestVersion();
        boolean areWeUpdated = latestVersion.equalsIgnoreCase(JavaEZ.VERSION) ;
        if(!areWeUpdated) {
            if(latestVersion.equalsIgnoreCase("Unknown")) {
                System.out.println("Could not check for new versions");
                return;
            }
            System.out.println("Attention: your JavaEZ is not at latest version, please consider updating!");
            System.out.println("Latest version: " + latestVersion);
        } else System.out.println("JavaEZ is up to date!");
}
```
