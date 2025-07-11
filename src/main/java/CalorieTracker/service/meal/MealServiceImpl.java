package CalorieTracker.service.meal;

import CalorieTracker.dao.MealRepository;
import CalorieTracker.entity.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;

    @Autowired
    public MealServiceImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Override
    public List<Meal> findAll() {
        return mealRepository.findAll();
    }

    @Override
    public Meal findById(Long id) {
        return mealRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Did not find meal id - " + id));
    }

    @Override
    public Meal save(Meal meal) {
        return mealRepository.save(meal);
    }

    @Override
    public void deleteById(Long id) {
        if (!mealRepository.existsById(id)) {
            throw new RuntimeException("Did not find meal id - " + id);
        }
        mealRepository.deleteById(id);
    }

    @Override
    public List<Meal> findByUsername(String username) {
        return mealRepository.findByUsername(username);
    }
}
