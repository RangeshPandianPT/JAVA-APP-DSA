def student_profile(name):
    marks_list = []
    
    def add_marks(subject, mark):
        marks_list.append(mark)
        
        average = sum(marks_list) / len(marks_list)
        
        print(f"Student: {name}")
        print(f"Added mark for {subject}: {mark}")
        print(f"Updated Average: {average:.2f}")
        print("-" * 20) 
    return add_marks

print("Creating profile for Alice...")
alice_profile_adder = student_profile("Alice")

print("Creating profile for Bob...")
bob_profile_adder = student_profile("Bob")

print("\n--- Adding marks for Alice ---")
alice_profile_adder("Math", 90)
alice_profile_adder("Science", 85)

print("\n--- Adding marks for Bob ---")
bob_profile_adder("History", 78)

print("\n--- Adding one more mark for Alice ---")
alice_profile_adder("English", 92)