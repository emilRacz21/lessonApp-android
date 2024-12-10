# LessonApp-Android

**LessonApp-Android** is an Android application designed to manage and display academic schedules. The app allows users to view their lessons for each week, including information on the start and end times, school names, and dates. It also allows users to interact with their schedules and modify personal information, such as their profile and language preferences.

## Features

- **Lesson Overview**: Displays weekly lesson schedules, showing both Monday and Friday of each week.
- **User Interaction**: Users can interact with their schedules by selecting a week and viewing details of individual lessons.
- **Profile and Language Settings**: Users can edit their profiles and change the app's language.
- **Custom Schedule**: Allows users to add and manage their own custom schedules via a dialog interface.
- **Database Integration**: Schedules are stored in a local database and can be retrieved or added as needed.
- **Fragment-Based Navigation**: The app uses fragments for seamless navigation between different sections (e.g., lessons, grades, more settings).

## How to Run

### Prerequisites

- **Android Studio**: To build and run the app, you need to have Android Studio installed on your computer.
- **Emulator or Device**: You can either use an Android Emulator (e.g., from Android Studio) or connect an Android device via USB for testing.

### Steps to Build and Run:

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/lessonapp-android.git

2. Open the project in Android Studio:
   - Launch Android Studio and select Open an existing project.
   - Navigate to the cloned project directory and select it.
    
3. Build the project:
   - Click on the Build button in Android Studio to compile the project.
     
4. Run the app:
   - Connect an Android device or use an Android Emulator.
   - Click on the Run button in Android Studio to install and launch the app.

### Key Features of the App:
- Lesson Fragment: Displays weekly lessons (Monday to Friday) and allows users to view the schedule for any given week.
- Lesson Select Fragment: Displays more detailed information about each individual lesson, including the duration and school.
- User Profile Management: Allows users to edit their profile information and change the app's language.
- Database Integration: Schedule data is stored in a local database and can be managed through a custom dialog interface to add new entries.

## How It Works

1. Weekly Lesson Overview:
    - The app generates a list of lessons for each week of the year, starting from Monday and ending on Friday.
    - These lessons are shown in the LessonFragment, where users can select a specific week and view more details.
  
2. Database Integration:
    - Schedules are stored in a local SQLite database.
    - The user can add a schedule entry via a custom dialog, which collects information such as the lesson time, school, and     date.
    - Once the schedule is added, it can be retrieved and displayed in the LessonSelectFragment.

3. User Profiles:
    - Users can manage their profiles, including changing their display name, and preferences.
    - Language settings can be modified to switch between different languages.

4. Action Bar & Navigation:
    - The app uses a custom action bar, and fragments are used for navigation between different sections such as "Lessons", "Grades", and "Settings".

## Design

![sch](https://github.com/user-attachments/assets/f00bf48d-2f36-483b-9201-e6c299859df6)

## License
This project is licensed under the MIT License - see the LICENSE file for details.
