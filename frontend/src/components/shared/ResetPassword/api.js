export const resetPasswordAPI = async (values, actions) => {
  console.log(values); // Debugging: Log form values
  const url = "http://localhost:8080/change"; // API endpoint

  try {
    const response = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        email: values.email,
        newPassword: values.newPassword,
      }),
    });

    if (!response.ok) {
      actions.setStatus("fail"); // Set status to fail
      throw new Error(`Failed with status: ${response.status}`);
    }

    const json = await response.json();
    actions.setStatus("success"); // Set status to success
    console.log(json); // Debugging: Log response
  } catch (error) {
    actions.setStatus("fail"); // Set status to fail
    console.error("Error:", error.message);
  }
};
