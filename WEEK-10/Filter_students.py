def filter_students(criteria, students):
    filtered_list = []
    for student in students:
        if criteria(student):
            filtered_list.append(student)
    return filtered_list

if __name__ == "__main__":
    all_students = [
        {"name": "Aditya Sharma", "cgpa": 9.1, "major": "Computer Science"},
        {"name": "Brenda Smith", "cgpa": 7.8, "major": "Mechanical"},
        {"name": "Charles Lee", "cgpa": 8.5, "major": "Computer Science"},
        {"name": "Diana Prince", "cgpa": 9.5, "major": "Electrical"},
        {"name": "Ethan Hunt", "cgpa": 7.2, "major": "Mechanical"},
    ]

    high_cgpa_students = filter_students(lambda s: s["cgpa"] > 8, all_students)
    print("Students with CGPA > 8:")
    print(high_cgpa_students)

    cs_students = filter_students(lambda s: s["major"] == "Computer Science", all_students)
    print("\nComputer Science Majors:")
    print(cs_students)
