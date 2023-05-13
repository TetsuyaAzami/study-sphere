import { ChangeEvent, useState, FocusEvent } from "react";

export const usePassword = () => {
  //
  const [password, setPassword] = useState<string>("");
  const [passwordInvalidLengthMessage, setPasswordInvalidLengthMessage] =
    useState<string>("");

  const minLength: number = 4;
  const maxLength: number = 128;

  const isPasswordValid = (password: string): boolean => {
    return minLength <= password.length && password.length <= maxLength;
  };

  const handlePasswordChange = (e: ChangeEvent<HTMLInputElement>): void => {
    setPassword(e.target.value);
  };

  const handlePasswordBlur = (e: FocusEvent<HTMLInputElement>): void => {
    //
    const value = e.target.value;

    if (!isPasswordValid(value)) {
      setPasswordInvalidLengthMessage(
        `パスワードは${minLength}文字以上${maxLength}文字以下で入力してください。`
      );
    } else {
      setPasswordInvalidLengthMessage("");
    }
  };

  return {
    password,
    isPasswordValid,
    maxLength,
    passwordInvalidLengthMessage,
    handlePasswordChange,
    handlePasswordBlur,
  };
};
