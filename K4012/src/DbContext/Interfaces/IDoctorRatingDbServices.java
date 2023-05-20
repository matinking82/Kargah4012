package DbContext.Interfaces;

import java.util.List;

import Models.DoctorRating;

public interface IDoctorRatingDbServices {
    public List<DoctorRating> getAllDoctorRatingsList();
    public DoctorRating getById(int Id);
    public boolean Add(DoctorRating doctorRating);
    public boolean Remove(int doctorRatingId);
    public boolean Remove(DoctorRating doctorRating);
    public boolean Update(DoctorRating doctorRating);
}
